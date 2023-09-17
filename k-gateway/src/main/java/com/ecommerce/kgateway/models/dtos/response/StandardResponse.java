package com.ecommerce.kgateway.models.dtos.response;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StandardResponse {
    private String statusCode;
    private String message;

}
