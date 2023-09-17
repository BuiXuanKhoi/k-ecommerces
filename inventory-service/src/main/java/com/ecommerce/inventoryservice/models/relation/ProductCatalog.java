package com.ecommerce.inventoryservice.models.relation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "product_catalog")
public class ProductCatalog {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "product_catalog_id")
    private UUID productCatalogId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_catalog_id")
    private Catalog catalog;

    @ManyToOne
    @JoinColumn(name = "fk_product_id")
    private Product product;
}
