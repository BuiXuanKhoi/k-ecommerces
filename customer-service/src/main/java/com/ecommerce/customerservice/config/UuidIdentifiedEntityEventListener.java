package com.ecommerce.customerservice.config;

import com.ecommerce.customerservice.models.UserDetail;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.UUID;

public class UuidIdentifiedEntityEventListener extends AbstractMongoEventListener<UserDetail> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<UserDetail> event) {
        super.onBeforeConvert(event);
        UserDetail userDetail = event.getSource();
        if (userDetail.getUserId() == null) userDetail.setUserId(UUID.randomUUID());
    }
}
