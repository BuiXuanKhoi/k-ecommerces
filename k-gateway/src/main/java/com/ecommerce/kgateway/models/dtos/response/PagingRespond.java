package com.ecommerce.kgateway.models.dtos.response;

import lombok.Builder;

@Builder
public class PagingRespond {
    private int capacity;
    private int currentPageNumber;
    private boolean isAsc;
}
