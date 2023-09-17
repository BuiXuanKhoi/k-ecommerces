package com.ecommerce.dataconnector.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.UUID;

@Document(indexName = "product-index")
public class ProductDetail {

    @Id
    @Field(type = FieldType.Keyword)
    private UUID id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Float)
    private Float unitPrice;

    @Field(type = FieldType.Long)
    private Long quantity;

    @Field(index = false, type = FieldType.Keyword)
    private UUID categoriesId;

}
