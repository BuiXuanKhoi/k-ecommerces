package com.ecommerce.inventoryservice.repositories.elasticsearch;

import com.ecommerce.inventoryservice.models.ProductDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductDocumentRepository extends ElasticsearchRepository<ProductDocument, String> {

    Page<ProductDocument> searchAllByName(String productName, Pageable pageable);
}
