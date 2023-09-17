package com.ecommerce.kgateway.controllers;


import com.ecommerce.kgateway.models.dtos.response.OrderResponse;
import com.ecommerce.kgateway.models.dtos.response.StandardResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping
    public List<OrderResponse> getAllOrder(){
        return new ArrayList<>();
    }

    @PostMapping
    public StandardResponse createOrder(){

        return null;
    }
}
