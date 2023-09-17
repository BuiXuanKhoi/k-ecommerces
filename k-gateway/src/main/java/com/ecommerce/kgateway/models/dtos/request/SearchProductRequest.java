package com.ecommerce.kgateway.models.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchProductRequest {
    private String name;
    private PagingRequest pagingRequest;
}
