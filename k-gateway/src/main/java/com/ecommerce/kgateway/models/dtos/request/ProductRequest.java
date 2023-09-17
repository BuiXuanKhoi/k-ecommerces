package com.ecommerce.kgateway.models.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequest {
    private String name;
    private String description;
    private int quantity;
    private double unitPrice;
    private List<UUID> categoriesId;
    private UUID ownerId;



}
