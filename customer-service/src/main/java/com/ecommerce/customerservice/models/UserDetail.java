package com.ecommerce.customerservice.models;

import com.ecommerce.customerservice.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class UserDetail {

    @Id
    private UUID userId;
    private String password;

    @Indexed(unique = true)
    private String username;

    private Information information;

    private Set<Role> userRoles;
    private double creditLimit;

    public UserDetail(String password, String username, Information information, double creditLimit) {
        this.password = password;
        this.username = username;
        this.information = information;
        this.creditLimit = creditLimit;
    }

    public UserDetail(String password, String username, Information information, Set<Role> userRoles, double creditLimit) {
        this.password = password;
        this.username = username;
        this.information = information;
        this.userRoles = userRoles;
        this.creditLimit = creditLimit;
    }
}

