package com.ecommerce.inventoryservice.repositories.jpa;

import com.ecommerce.inventoryservice.models.relation.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRelationalRepository extends JpaRepository<Product, UUID> {
}
