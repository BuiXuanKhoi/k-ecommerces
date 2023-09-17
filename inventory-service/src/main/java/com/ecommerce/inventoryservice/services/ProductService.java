package com.ecommerce.inventoryservice.services;

import com.ecommerce.inventoryservice.models.ProductDocument;
import com.ecommerce.inventoryservice.models.relation.Product;
import org.example.proto.generated.CreateProductRequest;
import org.example.proto.generated.ProductGetDetailRequest;
import org.example.proto.generated.SearchProductMessage;
import org.example.proto.generated.UpdateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    // Search service for elasticsearch
    ProductDocument searchById(UUID productId);

    Page<ProductDocument> searchByName(SearchProductMessage request);


    // Get service for postgresql

    Product getProductById(ProductGetDetailRequest request);

    Product getById(UUID productId);

    List<Product> getAll();

    List<Product> getMultiProductById(List<ProductGetDetailRequest> requests);


    // Create Product

    Product createProduct(CreateProductRequest request);

    List<Product> createMultiProducts(List<CreateProductRequest> requests);


    // Update Product
    Product updateProduct(UpdateProductRequest request);



    void deleteById(UUID id);



    void deleteMultiProductById(List<UUID> productIds);


    void checkout(UUID productId, int quantity);


}
