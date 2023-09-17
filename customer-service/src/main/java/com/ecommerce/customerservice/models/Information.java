package com.ecommerce.customerservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Information {
    private String email;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private String age;
    private String location;
    private String phoneNumber;
}
