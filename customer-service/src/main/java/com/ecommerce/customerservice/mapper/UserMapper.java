package com.ecommerce.customerservice.mapper;

import com.ecommerce.customerservice.models.Information;
import org.example.proto.generated.AuditMessage;
import org.example.proto.generated.UserInformation;

public class UserMapper {

    public UserInformation fromInformation(Information information, AuditMessage auditMessage, String username){
        return UserInformation
                .newBuilder()
                .setAudit(auditMessage)
                .setUsername(username)
                .setPhoneNumber(information.getPhoneNumber())
                .setFirstname(information.getFirstname())
                .setLastname(information.getLastname())
                .build();

    }
}
