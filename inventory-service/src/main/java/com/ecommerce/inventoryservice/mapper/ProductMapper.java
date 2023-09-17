package com.ecommerce.inventoryservice.mapper;

import com.ecommerce.inventoryservice.models.ProductDocument;
import com.ecommerce.inventoryservice.models.relation.Product;
import com.ecommerce.inventoryservice.services.ProductCatalogService;
import org.example.proto.generated.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private final ProductCatalogService productCatalogService;

    @Autowired
    public ProductMapper( @Lazy ProductCatalogService productCatalogService) {
        this.productCatalogService = productCatalogService;
    }

    public ProductDetail fromProductDocument(ProductDocument productDocument){
        return ProductDetail.newBuilder()
                .setId(productDocument.getProductId().toString())
                .setName(productDocument.getName())
                .setDescription(productDocument.getDescription())
                .setPrice(productDocument.getUnitPrice())
                .setOwnerId(productDocument.getOwnerId().toString()
                )
                .setCreateDate(new Date().toString())
                .setUpdateDate(new Date().toString())
                .build();
    }


    public ProductDetail toProductDetail(Product product){
        return ProductDetail.newBuilder()
                .setId(product.getProductId().toString())
                .setName(product.getName())
                .setDescription(product.getDescription())
                .setPrice(product.getUnitPrice())
                .setUpdateDate(product.getUpdateDate().toString())
                .setCreateDate(new Date().toString())
                .setOwnerId(product.getOwnerId().toString())
                .build();

    }



    public ProductDocument toProductDocument(CreateProductRequest request){
        return ProductDocument.builder()
                .productId(UUID.randomUUID())
                .name(request.getName())
                .description(request.getDescription())
                .quantity(Integer.toUnsignedLong(request.getQuantity()))
                .unitPrice(request.getPrice())
                .ownerId(UUID.fromString(request.getOwnerId()))
                .build();

    }

    public ProductDocument fromUpdateProductRequest(UpdateProductRequest request){
        CreateProductRequest createProductRequest = request.getCreateProductRequest();
        return ProductDocument.builder()
                .productId(UUID.fromString(request.getProductId()))
                .name(createProductRequest.getName())
                .description(createProductRequest.getDescription())
                .quantity(Integer.toUnsignedLong(createProductRequest.getQuantity()))
                .unitPrice(createProductRequest.getPrice())
                .ownerId(UUID.fromString(createProductRequest.getOwnerId()))
                .build();

    }


    public ProductDetail fromProduct(Product product){
        return ProductDetail.newBuilder()
                .setId(product.getProductId().toString())
                .setName(product.getName())
                .setDescription(product.getDescription())
                .setPrice(product.getUnitPrice())
                .setOwnerId(product.getOwnerId().toString())
                .setCreateDate(product.getCreateDate().toString())
                .setUpdateDate(product.getUpdateDate().toString())
                .build();
    }

    public Product toProduct(UpdateProductRequest request){
        CreateProductRequest createProductRequest = request.getCreateProductRequest();
        return Product.builder()
                .productId(UUID.fromString(request.getProductId()))
                .name(createProductRequest.getName())
                .description(createProductRequest.getDescription())
                .unitPrice((float) createProductRequest.getPrice())
                .quantity(createProductRequest.getQuantity())
                .ownerId(UUID.fromString(createProductRequest.getOwnerId()))
                .updateDate(new Date())
                .build();
    }

    public Product toProduct(CreateProductRequest createProductRequest) {
        return Product.builder()
                .name(createProductRequest.getName())
                .description(createProductRequest.getDescription())
                .unitPrice((float) createProductRequest.getPrice())
                .quantity(createProductRequest.getQuantity())
                .ownerId(UUID.fromString(createProductRequest.getOwnerId()))
                .updateDate(new Date())
                .build();
    }

    public Product fromCreateProductRequest(CreateProductRequest request){
        UUID newProductId = UUID.randomUUID();
        return Product.builder()
                .productId(newProductId)
                .name(request.getName())
                .description(request.getDescription())
                .ownerId(UUID.fromString(request.getOwnerId()))
                .quantity(request.getQuantity())
                .status(request.getStatus())
                .unitPrice((float) request.getPrice())
                .createDate(new Date())
                .updateDate(new Date())
                .productCatalogs(request.getCatalogIdList()
                        .stream()
                        .map(UUID::fromString)
                        .map(id -> productCatalogService.createRelation(newProductId, id))
                        .collect(Collectors.toList())
                )
                .build();

    }


    public ProductView toProductView(ProductDocument productDocument) {
        return ProductView.newBuilder()
                .setId(productDocument.getProductId().toString())
                .setName(productDocument.getName())
                .setProductAvatarUrl("")
                .setCatalogId("")
                .build();
    }


}
