package com.ecommerce.customerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventListener {

    @Bean
    public UuidIdentifiedEntityEventListener uuidIdentifiedEntityEventListener(){
        return new UuidIdentifiedEntityEventListener();
    }
}
