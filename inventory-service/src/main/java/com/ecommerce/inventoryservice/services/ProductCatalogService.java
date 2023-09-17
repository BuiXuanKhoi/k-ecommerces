package com.ecommerce.inventoryservice.services;

import com.ecommerce.inventoryservice.models.relation.ProductCatalog;

import java.util.UUID;

public interface ProductCatalogService {

    ProductCatalog createRelation(UUID productId, UUID catalogId );

}
