package com.ecommerce.dataconnector.repositories;

import com.ecommerce.dataconnector.models.ProductDetail;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

public interface ProductDetailRepository extends ElasticsearchRepository<ProductDetail, UUID> {
}
