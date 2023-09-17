package com.ecommerce.kgateway.models.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtDetail {
    private String token;
    private Date expiredTime;
    private RefreshToken refreshToken;
}
