package com.ecommerce.dataconnector.connector;

import com.ecommerce.dataconnector.models.ProductDetail;
import com.ecommerce.dataconnector.repositories.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ProductDetailConnector {

    private final ProductDetailRepository productDetailRepository;

    @Autowired
    public ProductDetailConnector(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }

    public void transfer(ProductDetail productDetail){
        this.productDetailRepository.save(productDetail);
    }
}
