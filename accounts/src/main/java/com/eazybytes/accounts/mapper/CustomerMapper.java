package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Customer;

public class CustomerMapper {
    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer){
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        return customer;

    }
    public static CustomerDto mapToCustomerDto( Customer customer,CustomerDto customerDto){
        customerDto.setMobileNumber(customer.getMobileNumber());
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        return customerDto;

    }

    public static CustomerDetailsDto maptoCustomerDetailsDto(Customer customer,CustomerDetailsDto customerDetailsDto) {
        customerDetailsDto.setMobileNumber(customer.getMobileNumber());
        customerDetailsDto.setName(customer.getName());
        customerDetailsDto.setEmail(customer.getEmail());
        return customerDetailsDto;
    }
}
