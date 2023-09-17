package com.ecommerce.kgateway;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(KGatewayApplication.class, args);
    }

}
