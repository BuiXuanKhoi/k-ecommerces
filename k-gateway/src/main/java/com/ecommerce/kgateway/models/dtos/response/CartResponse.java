package com.ecommerce.kgateway.models.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartResponse {
    private UUID cartId;
    private int quantity;
    private float totalPrice;
    private String currencyCode;
    private ProductResponse product;
}
