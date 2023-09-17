package com.ecommerce.kgateway.controllers.constants;

public class Routing {

    public static final String CUSTOMER_SERVICE = "localhost:" +  System.getenv("microservice.grpc.customer_service.port");
    private static final String INVENTORY_SERVICE  = "localhost:8082";
    private static final String ORDER_SERVICE = "localhost:8083";
    private static final String CHAT_SERVICE = "localhost:8084";
}
