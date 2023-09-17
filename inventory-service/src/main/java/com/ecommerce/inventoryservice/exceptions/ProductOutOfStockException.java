package com.ecommerce.inventoryservice.exceptions;


public class ProductOutOfStockException extends RuntimeException{

    public ProductOutOfStockException(String message) {
        super(message);
    }
}
