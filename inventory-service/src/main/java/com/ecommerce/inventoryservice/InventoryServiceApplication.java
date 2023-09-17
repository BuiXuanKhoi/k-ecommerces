package com.ecommerce.inventoryservice;

import com.ecommerce.inventoryservice.controllers.InventoryGrpcListener;
import com.ecommerce.inventoryservice.exceptions.GlobalExceptionHandler;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories("com.ecommerce.inventoryservice.repositories.jpa")
public class InventoryServiceApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
       ApplicationContext context = SpringApplication.run(InventoryServiceApplication.class, args);
        InventoryGrpcListener listener = context.getBean(InventoryGrpcListener.class);
        System.out.println("Before Start Server");

        Server server = ServerBuilder.forPort(8086)
                .addService(listener)
                .intercept(new GlobalExceptionHandler())
                .build();

        System.out.println("After start server");

        server.start();

    }

}
