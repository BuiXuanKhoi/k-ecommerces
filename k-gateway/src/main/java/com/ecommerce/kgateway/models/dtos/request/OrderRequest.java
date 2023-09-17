package com.ecommerce.kgateway.models.dtos.request;

import com.ecommerce.kgateway.controllers.constants.PaymentMethod;
import com.ecommerce.kgateway.models.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
public class OrderRequest extends AuditModel {

    private UUID cartId;
    private UUID userId;
    private PaymentMethod paymentMethod;

    public OrderRequest(Date createDate, Date updateDate, UUID cartId, UUID userId) {
        super(createDate, updateDate);
        this.cartId = cartId;
        this.userId = userId;
    }
}
