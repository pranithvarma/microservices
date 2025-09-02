package com.eazybytes.accounts.exception;

public class ResourceNotFoundException extends RuntimeException {


    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s resource with given input data is not present %s:%s ",resourceName,fieldName,fieldValue));
    }
}
