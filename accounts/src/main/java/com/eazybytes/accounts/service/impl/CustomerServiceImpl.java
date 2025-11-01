package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.*;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.CardsClient;
import com.eazybytes.accounts.service.ICustomerService;
import com.eazybytes.accounts.service.LoansClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final AccountsRepository accountsRepository;
    private final CardsClient cardsClient;
    private final LoansClient loansClient;

    @Override
    public CustomerDetailsDto fetchCustomer(String correlationId, String mobileNumber) {

        // 🧩 1. Fetch local data (blocking, DB)
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", Long.toString(customer.getCustomerId())));

        // Map to DTO
        CustomerDetailsDto customerDetailsDto = CustomerMapper.maptoCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        // 🧩 2. Run remote service calls in parallel
        CompletableFuture<ResponseEntity<CardsDto>> cardsFuture = CompletableFuture.supplyAsync(
                () -> cardsClient.fetchCardDetails(correlationId, mobileNumber)
        );

        CompletableFuture<ResponseEntity<LoansDto>> loansFuture = CompletableFuture.supplyAsync(
                () -> loansClient.fetchLoanDetails(correlationId, mobileNumber)
        );

        // 🧩 3. Wait for both to complete and combine
        CompletableFuture.allOf(cardsFuture, loansFuture).join();

        // Set results
        ResponseEntity<CardsDto> cardsResponse = cardsFuture.join();
        if (cardsResponse != null && cardsResponse.getBody() != null) {
            customerDetailsDto.setCardsDto(cardsResponse.getBody());
        }

        ResponseEntity<LoansDto> loansResponse = loansFuture.join();
        if (loansResponse != null && loansResponse.getBody() != null) {
            customerDetailsDto.setLoansDto(loansResponse.getBody());
        }

        return customerDetailsDto;
    }
}
