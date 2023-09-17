package com.ecommerce.inventoryservice.services;

import com.ecommerce.inventoryservice.models.relation.ProductCatalog;
import com.ecommerce.inventoryservice.repositories.jpa.ProductCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductCatalogServiceImpl implements ProductCatalogService{

    private final ProductCatalogRepository productCatalogRepository;
    private final ProductService productService;
    private final CatalogService catalogService;

    @Autowired
    public ProductCatalogServiceImpl(ProductCatalogRepository productCatalogRepository, ProductService productService, CatalogService catalogServices) {
        this.productCatalogRepository = productCatalogRepository;
        this.productService = productService;
        this.catalogService = catalogServices;
    }

    @Override
    public ProductCatalog createRelation(UUID productId, UUID catalogId) {
        ProductCatalog productCatalog = ProductCatalog.builder()
                .productCatalogId(UUID.randomUUID())
                .product(productService.getById(productId))
                .catalog(catalogService.findById(catalogId))
                .build();
        return this.productCatalogRepository.save(productCatalog);
    }
}
