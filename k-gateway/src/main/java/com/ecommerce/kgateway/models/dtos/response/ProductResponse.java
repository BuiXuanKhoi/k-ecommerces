package com.ecommerce.kgateway.models.dtos.response;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private String ownerId;
    private double price;
}
