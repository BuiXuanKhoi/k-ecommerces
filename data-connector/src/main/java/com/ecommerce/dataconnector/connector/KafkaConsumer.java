package com.ecommerce.dataconnector.connector;

import com.ecommerce.dataconnector.models.ProductDetail;
import com.ecommerce.dataconnector.repositories.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final ProductDetailRepository productDetailRepository;

    @Autowired
    public KafkaConsumer(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }

    @KafkaListener(topics = "schema-changes")
    public void writeData(ProductDetail productDetail) {
        this.productDetailRepository.save(productDetail);
    }
}
