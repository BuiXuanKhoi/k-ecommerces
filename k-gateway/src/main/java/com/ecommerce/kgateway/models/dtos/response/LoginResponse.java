package com.ecommerce.kgateway.models.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginResponse extends StandardResponse{
    private String username;
    private JwtDetail jwtDetail;

    public LoginResponse(String statusCode, String message, String username, JwtDetail jwtDetail) {
        super(statusCode, message);
        this.username = username;
        this.jwtDetail = jwtDetail;
    }
}
