package com.ecommerce.inventoryservice.models.relation;

import com.ecommerce.inventoryservice.models.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.proto.generated.ProductStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "products")
@SuperBuilder
public class Product extends AuditModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_description", columnDefinition = "text")
    private String description;

    @Column(name = "product_unit_price")
    private float unitPrice;

    @Column(name = "product_quantity")
    private long quantity;

    @Column(name = "product_owner_id")
    private UUID ownerId;

    @Column(name = "product_status")
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductCatalog> productCatalogs;
}
