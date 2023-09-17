package com.ecommerce.kgateway.controllers;


import com.ecommerce.kgateway.mapper.ProductMapper;
import com.ecommerce.kgateway.models.dtos.request.ProductRequest;
import com.ecommerce.kgateway.models.dtos.response.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.proto.generated.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductMapper mapper;

    @Value("${microservice.grpc.inventory_service.port}")
    private String productGrpcHost;

    @Autowired
    public ProductController(ProductMapper mapper) {

        this.mapper = mapper;
    }

    private ProductGRPCGrpc.ProductGRPCBlockingStub createStub(String grpcHost){
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8086")
                .usePlaintext()
                .build();
        return ProductGRPCGrpc.newBlockingStub(channel);
    }



    @GetMapping
    public List<ProductResponse> getAllProduct(){

        ProductGRPCGrpc.ProductGRPCBlockingStub stub = createStub(productGrpcHost);

        EmptyMessage emptyMessage = EmptyMessage.getDefaultInstance();
        ProductsDetail productsDetail =  stub.getAllProducts(emptyMessage);
        return this.mapper.fromProductsDetail(productsDetail);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductDetail(@PathVariable UUID id) {
        ProductGetDetailRequest productGetDetailRequest = ProductGetDetailRequest.newBuilder()
                .setId(id.toString())
                .build();

        ProductDetail productDetail = createStub(productGrpcHost).getProductDetailById(productGetDetailRequest);

        return this.mapper.fromProductDetailGrpc(productDetail);
    }

    @PostMapping
    public ProductResponse createNewProduct(@RequestBody ProductRequest request){
        System.out.println("Request");
        CreateProductRequest createProductRequest = mapper.fromProductRequest(request);

        ProductDetail productDetail = createStub(productGrpcHost).createProduct(createProductRequest);
        return mapper.fromProductDetailGrpc(productDetail);
    }

    @PatchMapping("/{id}")
    public ProductResponse updateProduct(
            @RequestBody ProductRequest request,
            @PathVariable UUID id
            ){

        UpdateProductRequest updateProductRequest = mapper.fromProductRequest(request, id);
        ProductDetail productDetail = createStub(productGrpcHost).updateProduct(updateProductRequest);
        return mapper.fromProductDetailGrpc(productDetail);
    }


    @DeleteMapping("/{id}")
    public StandardResponse deleteProduct(@PathVariable UUID id){
        ProductGetDetailRequest request = ProductGetDetailRequest.newBuilder()
                        .setId(id.toString()).build();
        StandardRespond respond = createStub(productGrpcHost).removeProductById(request);
        return StandardResponse.builder()
                .statusCode("200")
                .message(respond.getMessage()).build();
    }

    @GetMapping("/search")
    public SearchResult searchProduct(
            @RequestParam(value = "key", defaultValue = "", required = false) String keyWord,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "cap", defaultValue = "10") int capacity,
            @RequestParam(value = "sort", defaultValue = "asc") String sort
    ){
        PagingMessage pagingMessage = PagingMessage.newBuilder()
                .setAction(PagingAction.NEXT)
                .setCapacity(capacity)
                .setCurrentPage(0)
                .setIsAsc(sort.equals("asc"))
                .setNextPage(page)
                .addAllSortCriteria(Arrays.asList(Criteria.NAME))
                .build();

        SearchProductMessage searchProductMessage = SearchProductMessage.newBuilder()
                .setName(keyWord)
                .setPagingMessage(pagingMessage)
                .build();

        SearchProductResult result = createStub(productGrpcHost).searchProductByName(searchProductMessage);
        PagingRespond pagingRespond = PagingRespond.builder()
                .capacity(result.getPagingResponse().getCapacity())
                .currentPageNumber(result.getPagingResponse().getCurrentPage())
                .isAsc(result.getPagingResponse().getIsAsc()).build();

        List<ProductInformation> productInformations = result.getProductViewList().stream()
                .map(mapper::fromProductView)
                .collect(Collectors.toList());



        return SearchResult.builder()
                .view(Collections.singletonList(productInformations))
                .pagingRespond(pagingRespond)
                .build();
    }

}
