package com.ecommerce.inventoryservice.exceptions;

import lombok.*;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
