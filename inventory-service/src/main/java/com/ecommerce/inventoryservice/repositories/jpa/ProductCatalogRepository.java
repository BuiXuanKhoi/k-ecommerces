package com.ecommerce.inventoryservice.repositories.jpa;

import com.ecommerce.inventoryservice.models.relation.ProductCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductCatalogRepository extends JpaRepository<ProductCatalog, UUID> {

}
