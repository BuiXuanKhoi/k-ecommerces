package com.ecommerce.kgateway.models.dtos.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public class ProductInformation {
    private UUID id;
    private String name;
    private String avatarUrl;
    private float unitPrice;
    private int quantity;
}
