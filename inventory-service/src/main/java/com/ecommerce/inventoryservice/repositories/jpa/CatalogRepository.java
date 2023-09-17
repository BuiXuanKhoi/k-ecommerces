package com.ecommerce.inventoryservice.repositories.jpa;

import com.ecommerce.inventoryservice.models.relation.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, UUID> {
}
