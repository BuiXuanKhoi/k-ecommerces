package com.ecommerce.kgateway.mapper;

import com.ecommerce.kgateway.models.dtos.request.ProductRequest;
import com.ecommerce.kgateway.models.dtos.response.ProductInformation;
import com.ecommerce.kgateway.models.dtos.response.ProductResponse;
import org.example.proto.generated.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductResponse fromProductDetailGrpc(ProductDetail productDetail){
        return ProductResponse.builder()
                .id(productDetail.getId())
                .name(productDetail.getName())
                .ownerId(productDetail.getOwnerId())
                .price(productDetail.getPrice())
                .description(productDetail.getDescription())
                .build();

    }


    public UpdateProductRequest fromProductRequest(ProductRequest request, UUID uuid){
        CreateProductRequest createProductRequest = fromProductRequest(request);
        return UpdateProductRequest.newBuilder()
                .setProductId(uuid.toString())
                .setCreateProductRequest(createProductRequest)
                .build();
    }

    public CreateProductRequest fromProductRequest(ProductRequest request){
        return CreateProductRequest.newBuilder()
                .setName(request.getName())
                .setDescription(request.getDescription())
                .setPrice(request.getUnitPrice())
                .setQuantity(request.getQuantity())
                .setOwnerId(request.getOwnerId().toString())
                .setStatus(ProductStatus.EXISTED)
                .addAllCatalogId(request.getCategoriesId().stream()
                        .map(UUID::toString)
                        .collect(Collectors.toList()))
                .build();
    }

    public List<ProductResponse> fromProductsDetail(ProductsDetail productsDetail){
        return productsDetail.getProductsDetailList()
                .stream().map(
                        detail -> ProductResponse.builder()
                                .id(detail.getId())
                                .name(detail.getName())
                                .description(detail.getDescription())
                                .price(detail.getPrice())
                                .ownerId(detail.getOwnerId())
                                .build()
                ).collect(Collectors.toList());

    }

    public ProductInformation fromProductView(ProductView productView){
        return ProductInformation.builder()
                .id(UUID.fromString(productView.getId()))
                .name(productView.getName())
                .unitPrice(Float.parseFloat(productView.getUnitPrice()))
                .quantity(Integer.parseInt(productView.getQuantity()))
                .avatarUrl(productView.getProductAvatarUrl())
                .build();
    }
}
