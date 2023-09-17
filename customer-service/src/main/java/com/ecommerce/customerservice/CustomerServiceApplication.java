package com.ecommerce.customerservice;

import com.ecommerce.customerservice.controllers.CustomerGRPCListener;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class CustomerServiceApplication {

    @Value("${grpc.port}")
    static int gRPCPort;

    public static void main(String[] args) throws IOException, InterruptedException {
        ApplicationContext context = SpringApplication.run(CustomerServiceApplication.class, args);
        CustomerGRPCListener listener = context.getBean(CustomerGRPCListener.class);
        System.out.println(gRPCPort);
        System.out.println(System.getenv("grpc.port"));
        Server server = ServerBuilder.forPort(8085)
                .addService(listener)
                .build();

        server.start();

        server.awaitTermination();
    }

}
