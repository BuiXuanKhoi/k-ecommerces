package com.ecommerce.inventoryservice.services;

import com.ecommerce.inventoryservice.exceptions.ProductOutOfStockException;
import com.ecommerce.inventoryservice.exceptions.ResourceNotFoundException;
import com.ecommerce.inventoryservice.mapper.ProductMapper;
import com.ecommerce.inventoryservice.models.ProductDocument;
import com.ecommerce.inventoryservice.models.relation.Product;
import com.ecommerce.inventoryservice.repositories.elasticsearch.ProductDocumentRepository;
import com.ecommerce.inventoryservice.repositories.jpa.ProductRelationalRepository;
import org.example.proto.generated.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional ;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductMapper productMapper;

    private final ProductDocumentRepository productDocumentRepository;
    private final ProductRelationalRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductDocumentRepository productDocumentRepository,
                              ProductMapper productMapper, ProductRelationalRepository productRepository) {
        this.productDocumentRepository = productDocumentRepository;
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public ProductDocument searchById(UUID productId) {
        return this.productDocumentRepository.findById(productId.toString())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Not Found Product With ID: " + productId)
                );
    }

    @Override
    @Transactional
    public Page<ProductDocument> searchByName(SearchProductMessage request) {
        PagingMessage pagingMessage = request.getPagingMessage();
        String productName = request.getName();
        Pageable pageable = PageRequest.of(pagingMessage.getNextPage(), pagingMessage.getCapacity());
        return this.productDocumentRepository.searchAllByName(productName, pageable);
    }


    @Override
    @Transactional
    public Product createProduct(CreateProductRequest request) {
        Product productDocument = this.productMapper.toProduct(request);
        return this.productRepository.save(productDocument);
    }

    @Override
    @Transactional
    public Product  getProductById(ProductGetDetailRequest request) {
        UUID id = UUID.fromString(request.getId());
        return getById(id);
    }

    @Override
    @Transactional
    public List<Product> getMultiProductById(List<ProductGetDetailRequest> requests) {
        return requests.stream().map(this::getProductById).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Product> createMultiProducts(List<CreateProductRequest> requests) {
        return  requests.stream()
                .map(this::createProduct)
                .collect(Collectors.toList());
    }



    @Override
    @Transactional
    public void deleteById(UUID id) {
        Product selectedProduct = getById(id);
        this.productRepository.delete(selectedProduct);
    }

    @Override
    @Transactional
    public Product updateProduct(UpdateProductRequest request) {
        Product productDocument = this.productMapper.toProduct(request);
        return this.productRepository.save(productDocument);
    }

    @Override
    @Transactional
    public void deleteMultiProductById(List<UUID> productIds) {
        productIds.forEach(this::deleteById);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getById(UUID productId) {
        return this.productRepository.findById(productId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("There's no product exist !!!")
                );
    }

    @Override
    public void checkout(UUID productId, int quantity) {
        long updatedQuantity;
        Product product = getById(productId);
        long baseQuantity = product.getQuantity();
        if (baseQuantity < quantity) throw new ProductOutOfStockException(String.join("Product With ID: ",productId.toString(), " is out of stock"));
        updatedQuantity = baseQuantity - quantity;

        if (updatedQuantity == 0) product.setStatus(ProductStatus.OUT_OF_STOCK);

        this.productRepository.save(product);

    }
}
