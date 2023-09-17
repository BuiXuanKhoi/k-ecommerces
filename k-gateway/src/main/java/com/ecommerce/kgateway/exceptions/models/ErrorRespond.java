package com.ecommerce.kgateway.exceptions.models;

import java.util.Map;

public class ErrorRespond extends HttpRespond {

    public ErrorRespond(int statusCode, String message) {
        super(statusCode, message);
    }
}
