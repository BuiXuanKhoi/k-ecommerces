package com.ecommerce.kgateway.controllers;

import com.ecommerce.kgateway.controllers.constants.Routing;
import com.ecommerce.kgateway.models.dtos.request.LoginRequest;
import com.ecommerce.kgateway.models.dtos.request.SignupRequest;
import com.ecommerce.kgateway.models.dtos.response.*;
import com.ecommerce.kgateway.models.dtos.response.JwtDetail;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.proto.generated.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Value("${microservice.grpc.customer_service.port}")
    private int port;



    @PostMapping("/signup")
    public SuccessResponse signup(@RequestBody SignupRequest request){
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + port)
                .usePlaintext()
                .build();

        CustomerGRPCGrpc.CustomerGRPCBlockingStub stub = CustomerGRPCGrpc.newBlockingStub(channel);
        SignupMessage message = SignupMessage.newBuilder()
                .setDateOfBirth(new Date().toString())
                .setUsername(request.getUsername())
                .setPassword(request.getPassword())
                .setEmailAddress(request.getEmailAddress())
                .setPhoneNumber(request.getPhoneNumber())
                .setFirstname(request.getFirstname())
                .setLastname(request.getLastname())
                .build();
        stub.signup(message);
        return new SuccessResponse("201", "Signup Success !!!");
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + port)
                .usePlaintext()
                .build();
        CustomerGRPCGrpc.CustomerGRPCBlockingStub stub = CustomerGRPCGrpc.newBlockingStub(channel);

        LoginMessage loginMessage = LoginMessage.newBuilder()
                .setUsername(request.getUsername())
                .setPassword(request.getPassword())
                .build();

        LoginDetail loginDetail = stub.login(loginMessage);
        RefreshToken refreshToken = new RefreshToken(
                loginDetail.getJwtDetail().getRefreshToken(),
                new Date()
        );
        JwtDetail jwtDetail = new JwtDetail(
                loginDetail.getJwtDetail().getToken(),
                new Date(),
                refreshToken
        );

        return new LoginResponse(
                "200",
                "Login Success",
                loginDetail.getInformation().getUsername(),
                jwtDetail
        );
    }



}
