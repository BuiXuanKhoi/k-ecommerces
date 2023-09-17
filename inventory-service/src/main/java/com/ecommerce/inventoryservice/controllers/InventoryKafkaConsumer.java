package com.ecommerce.inventoryservice.controllers;


import com.ecommerce.inventoryservice.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryKafkaConsumer {
    private final Logger logger = LoggerFactory.getLogger(InventoryKafkaConsumer.class);

    private final ProductService productService;

    @Autowired
    public InventoryKafkaConsumer(ProductService productService) {
        this.productService = productService;
    }

    @KafkaListener(topics = "product-topic")
    public void listenToCheckOutEvent(String message){

    }

}
