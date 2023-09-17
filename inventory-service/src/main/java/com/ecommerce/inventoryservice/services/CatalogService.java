package com.ecommerce.inventoryservice.services;

import com.ecommerce.inventoryservice.models.relation.Catalog;

import java.util.UUID;

public interface CatalogService {

    Catalog findById(UUID catalogId);
}
