package com.ecommerce.customerservice.services;

import com.ecommerce.customerservice.constants.Role;
import com.ecommerce.customerservice.exceptions.WrongCredentialException;
import com.ecommerce.customerservice.mapper.UserMapper;
import com.ecommerce.customerservice.models.Information;
import com.ecommerce.customerservice.models.UserDetail;
import com.ecommerce.customerservice.repositories.CustomerRepository;
import com.ecommerce.customerservice.utils.JwtUtil;
import org.example.proto.generated.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Service
public class AuthServiceImpl implements AuthService {

    private final CustomerRepository customerRepository;

    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;



    @Autowired
    public AuthServiceImpl(CustomerRepository customerRepository, JwtUtil jwtUtil) {
        this.customerRepository = customerRepository;
        this.encoder = new BCryptPasswordEncoder();
        this.jwtUtil = jwtUtil;
        this.userMapper = new UserMapper();
    }

    @Override
    public UserDetail findByUsername(String username) {
        return this.customerRepository.findByUsername(username)
                .orElseThrow(
                        () -> new NullPointerException()
                );
    }

    @Override
    public LoginDetail login(LoginMessage loginMessage) {
        String username = loginMessage.getUsername();
        String rawPassword = loginMessage.getPassword();
        UserDetail userDetail = findByUsername(username);
        Information information = userDetail.getInformation();

        String actualPassword = userDetail.getPassword();

        if (!encoder.matches(rawPassword, actualPassword))
            throw new WrongCredentialException("Wrong Password !!!");

        AuditMessage auditMessage = AuditMessage.newBuilder()
                .setCreatedDate(new Date().toString())
                .build();

        UserInformation userInformation = userMapper.fromInformation(information, auditMessage, username);

        JwtDetail jwtDetail = jwtUtil.generateTokenWithUserName(username);

        return LoginDetail.newBuilder()
                          .setJwtDetail(jwtDetail)
                          .setInformation(userInformation)
                          .build();
    }

    @Override
    public UserDetail signup(SignupMessage signupMessage) {
        String username = signupMessage.getUsername();
        String rawPassword = signupMessage.getPassword();

        Information information = Information.builder()
                .firstname(signupMessage.getFirstname())
                .lastname(signupMessage.getLastname())
                .dateOfBirth(new Date())
                .phoneNumber(signupMessage.getPhoneNumber())
                .location("HCM City")
                .age("30")
                .email(signupMessage.getEmailAddress())
                .build();


        UserDetail userDetail = new UserDetail(
                encoder.encode(rawPassword),
                username,
                information,
                new HashSet<Role>(Arrays.asList(Role.CLIENT)),
                100000.000
        );
        return this.customerRepository.save(userDetail);
    }
}
