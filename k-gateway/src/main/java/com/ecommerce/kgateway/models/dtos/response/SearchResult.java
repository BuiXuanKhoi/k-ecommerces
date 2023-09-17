package com.ecommerce.kgateway.models.dtos.response;

import lombok.Builder;
import org.example.proto.generated.PagingResponse;

import java.util.List;

@Builder
public class SearchResult <T>{
    private List<T> view;
    private PagingRespond pagingRespond;
}
