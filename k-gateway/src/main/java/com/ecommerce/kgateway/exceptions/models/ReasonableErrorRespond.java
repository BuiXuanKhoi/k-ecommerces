package com.ecommerce.kgateway.exceptions.models;

import java.util.Map;

public class ReasonableErrorRespond extends HttpRespond{
    private Map<String, String> reasons;


    public ReasonableErrorRespond(int statusCode, String message, Map<String, String> reasons) {
        super(statusCode, message);
        this.reasons = reasons;
    }
}
