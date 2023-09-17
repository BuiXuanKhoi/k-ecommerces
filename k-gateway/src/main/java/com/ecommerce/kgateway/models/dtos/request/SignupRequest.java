package com.ecommerce.kgateway.models.dtos.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Data
public class SignupRequest {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String emailAddress;
}
