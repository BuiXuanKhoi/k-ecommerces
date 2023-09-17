package com.ecommerce.kgateway.models.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proto.generated.Criteria;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagingRequest {
    private int capacity;
    private int currentPageNumber;
    private int previousPageNumber;
    private int nextPageNumber;

    private boolean isAsc;
    private Criteria sortBy;
}
