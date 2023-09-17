package com.ecommerce.kgateway.models.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse extends StandardResponse{

    public SuccessResponse(String statusCode, String message) {
        super(statusCode, message);
    }
}
