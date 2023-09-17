package com.ecommerce.customerservice.controllers;

import com.ecommerce.customerservice.models.Information;
import com.ecommerce.customerservice.models.UserDetail;
import com.ecommerce.customerservice.services.AuthService;
import io.grpc.stub.StreamObserver;
import org.example.proto.generated.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CustomerGRPCListener extends CustomerGRPCGrpc.CustomerGRPCImplBase {
    private final AuthService authService;

    @Autowired
    public CustomerGRPCListener(AuthService authService) {
        super();
        this.authService = authService;
    }

    @Override
    public void login(LoginMessage request, StreamObserver<LoginDetail> responseObserver) {
        LoginDetail loginDetail = authService.login(request);
        responseObserver.onNext(loginDetail);
        responseObserver.onCompleted();
    }

    @Override
    public void signup(SignupMessage request, StreamObserver<UserInformation> responseObserver) {
        UserDetail userDetail = authService.signup(request);
        Information information = userDetail.getInformation();

        AuditMessage auditMessage = AuditMessage.newBuilder()
                .setCreatedDate(new Date().toString())
                .build();

        UserInformation userInformation =
                UserInformation.newBuilder()
                               .setFirstname(information.getFirstname())
                               .setLastname(information.getLastname())
                               .setPhoneNumber(information.getPhoneNumber())
                               .setUsername(userDetail.getUsername())
                               .setAudit(auditMessage).build();

        responseObserver.onNext(userInformation);
        responseObserver.onCompleted();
    }

    @Override
    public void updateInformation(UpdateInformationMessage request, StreamObserver<UserInformation> responseObserver) {
        super.updateInformation(request, responseObserver);
    }

    @Override
    public void changePassword(ChangePasswordMessage request, StreamObserver<StandardMessage> responseObserver) {
        super.changePassword(request, responseObserver);
    }

    @Override
    public void remindPassword(RemindPasswordMessage request, StreamObserver<StandardMessage> responseObserver) {
        super.remindPassword(request, responseObserver);
    }

    @Override
    public void retakePassword(RetakePasswordMessage request, StreamObserver<StandardMessage> responseObserver) {
        super.retakePassword(request, responseObserver);
    }

    @Override
    public void withDraw(WithDrawMessage request, StreamObserver<StandardMessage> responseObserver) {
        super.withDraw(request, responseObserver);
    }

    @Override
    public void topUp(TopUpMessage request, StreamObserver<StandardMessage> responseObserver) {
        super.topUp(request, responseObserver);
    }
}
