package com.ecommerce.kgateway.models.dtos.response;

import com.ecommerce.kgateway.models.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
public class OrderResponse extends AuditModel {
    private String orderId;
    private ProductResponse productDetail;
    private boolean isCompleted;

    public OrderResponse(Date createDate, Date updateDate, String orderId, ProductResponse productDetail, boolean isCompleted) {
        super(createDate, updateDate);
        this.orderId = orderId;
        this.productDetail = productDetail;
        this.isCompleted = isCompleted;
    }
}
