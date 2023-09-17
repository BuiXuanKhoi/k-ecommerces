package com.ecommerce.kgateway.exceptions.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HttpRespond {
    private int statusCode;
    private String message;
}
