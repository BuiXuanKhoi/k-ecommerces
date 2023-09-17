package com.ecommerce.inventoryservice.controllers;

import com.ecommerce.inventoryservice.mapper.ProductMapper;
import com.ecommerce.inventoryservice.models.ProductDocument;
import com.ecommerce.inventoryservice.models.relation.Product;
import com.ecommerce.inventoryservice.services.ProductService;
import io.grpc.stub.StreamObserver;
import org.example.proto.generated.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class InventoryGrpcListener extends ProductGRPCGrpc.ProductGRPCImplBase {

    private final ProductService productService;
    private final ProductMapper mapper;

    @Autowired
    public InventoryGrpcListener(ProductService productService, ProductMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @Override
    public void getProductDetailById(ProductGetDetailRequest request, io.grpc.stub.StreamObserver<ProductDetail> responseObserver) {
        Product product = this.productService.getProductById(request);
        responseObserver.onNext(mapper.toProductDetail(product));
        responseObserver.onCompleted();
    }

    @Override
    public void getMultiProductDetailById(ProductsGetDetailRequest request, io.grpc.stub.StreamObserver<ProductsDetail> responseObserver) {
        super.getMultiProductDetailById(request, responseObserver);
    }

    @Override
    public void getAllProducts(EmptyMessage request, io.grpc.stub.StreamObserver<ProductsDetail> responseObserver) {
        List<Product> products = this.productService.getAll();
        ProductsDetail detail = ProductsDetail.newBuilder()
                .addAllProductsDetail(products.stream()
                        .map(mapper::toProductDetail)
                        .collect(Collectors.toList()))
                .build();

        responseObserver.onNext(detail);
        responseObserver.onCompleted();
    }

    @Override
    public void removeProductById(ProductGetDetailRequest request, io.grpc.stub.StreamObserver<StandardRespond> responseObserver) {
        String id = request.getId();
        this.productService.deleteById(UUID.fromString(id));
        StandardRespond respond = StandardRespond.newBuilder()
                        .setStatusCode(200).setMessage("Delete Success !!!").build();
        responseObserver.onNext(respond);
        responseObserver.onCompleted();
    }

    @Override
    public void removeMultiProductById(ProductsGetDetailRequest request, io.grpc.stub.StreamObserver<StandardRespond> responseObserver) {
        super.removeMultiProductById(request, responseObserver);
    }

    @Override
    public void createProduct(CreateProductRequest request, io.grpc.stub.StreamObserver<ProductDetail> responseObserver) {
        Product product = this.productService.createProduct(request);
        responseObserver.onNext(mapper.toProductDetail(product));
        responseObserver.onCompleted();
    }

    @Override
    public void createMultiProduct(CreateProductsRequest request, io.grpc.stub.StreamObserver<ProductsDetail> responseObserver) {
        List<CreateProductRequest> requests = request.getCreateProductsRequestList();
        List<Product> products = this.productService.createMultiProducts(requests);
        ProductsDetail productsDetail = ProductsDetail.newBuilder().addAllProductsDetail(products.stream()
                .map(mapper::toProductDetail)
                .collect(Collectors.toList()))
                .build();
        responseObserver.onNext(productsDetail);
        responseObserver.onCompleted();
    }

    @Override
    public void updateProduct(UpdateProductRequest request, io.grpc.stub.StreamObserver<ProductDetail> responseObserver) {
        Product product = this.productService.updateProduct(request);
        responseObserver.onNext(mapper.toProductDetail(product));
        responseObserver.onCompleted();
    }

    @Override
    public void updateMultiProduct(UpdateProductsRequest request, io.grpc.stub.StreamObserver<ProductsDetail> responseObserver) {
        super.updateMultiProduct(request, responseObserver);
    }

    @Override
    public void sellProduct(SellProductRequest request, io.grpc.stub.StreamObserver<StandardRespond> responseObserver) {
        super.sellProduct(request, responseObserver);
    }

    @Override
    public void searchProductByName(SearchProductMessage request, StreamObserver<SearchProductResult> responseObserver) {
        Page<ProductDocument> searchResult = this.productService.searchByName(request);
        List<ProductView> productViews = searchResult.getContent().stream()
                .map(mapper::toProductView)
                        .collect(Collectors.toList());

        PagingResponse pagingResponse = PagingResponse.newBuilder()
                .setCapacity(10)
                .setCurrentPage(10)
                .setPreviousPage(9)
                .setIsAsc(false)
                .setIsSort(false)
                .addAllSortCriteria(List.of(Criteria.NAME))
                .addAllFilterCriteria(List.of(Criteria.NAME)).build();

        SearchProductResult result = SearchProductResult.newBuilder()
                .setPagingResponse(pagingResponse).addAllProductView(productViews).build();

        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }
}
