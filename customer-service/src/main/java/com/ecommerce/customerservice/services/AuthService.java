package com.ecommerce.customerservice.services;

import com.ecommerce.customerservice.models.UserDetail;
import org.example.proto.generated.LoginDetail;
import org.example.proto.generated.LoginMessage;
import org.example.proto.generated.SignupMessage;

public interface AuthService {

    UserDetail findByUsername(String username);
    LoginDetail login(LoginMessage loginMessage);

    UserDetail signup(SignupMessage signupMessage);
}
