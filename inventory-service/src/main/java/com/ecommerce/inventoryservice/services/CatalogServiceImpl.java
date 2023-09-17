package com.ecommerce.inventoryservice.services;

import com.ecommerce.inventoryservice.exceptions.ResourceNotFoundException;
import com.ecommerce.inventoryservice.models.relation.Catalog;
import com.ecommerce.inventoryservice.repositories.jpa.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CatalogServiceImpl implements CatalogService{


    private final CatalogRepository catalogRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public Catalog findById(UUID catalogId) {
        return this.catalogRepository.findById(catalogId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("There's no catalog existed !!!")
                );
    }
}
