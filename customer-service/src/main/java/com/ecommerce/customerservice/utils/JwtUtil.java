package com.ecommerce.customerservice.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.example.proto.generated.JwtDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPublicKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.public.key}")
    private String jwtPublicKey;

    @Value("${jwt.secret.key}")
    private String jwtPrivateKey;

    @Value("${jwt.expired.time}")
    private Long expiredTime;

    private final Algorithm ALGORITHM;

    public JwtUtil() {
        this.ALGORITHM = Algorithm.HMAC256("born-in-2000");
    }

    public JwtDetail generateTokenWithUserName(String username){
        long expirationTime = new Date().getTime() + expiredTime;
        Date expiredAt = new Date(expirationTime);

        String token =  JWT.create()
                .withIssuer(jwtPrivateKey)
                .withClaim("username", username)
                .withExpiresAt(expiredAt)
                .sign(ALGORITHM);

        return JwtDetail.newBuilder()
                .setToken(token)
                .setExpiredTime(expiredAt.toString())
                .setRefreshToken("123").build();
    }
}
