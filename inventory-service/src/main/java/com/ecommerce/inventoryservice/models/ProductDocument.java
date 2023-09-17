package com.ecommerce.inventoryservice.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.GeneratedValue;
import java.util.UUID;

@Document(indexName = "product-index")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ProductDocument {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Field(type = FieldType.Keyword, index = false)
    private UUID productId;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String name;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String description;

    @Field(type = FieldType.Double)
    private double unitPrice;

    @Field(type = FieldType.Long)
    private Long quantity;

    @Field(type = FieldType.Keyword)
    private UUID ownerId;
}
