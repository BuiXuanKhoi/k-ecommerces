package com.ecommerce.inventoryservice.models.relation;

import com.ecommerce.inventoryservice.models.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "catalogs")
public class Catalog extends AuditModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "catalog_id")
    private String catalogId;

    @Column(name = "catalog_capacity")
    private Long capacity;

    @Column(name = "catalog_description")
    private String description;

    @Column(name = "catalog_name")
    private String name;

    @OneToMany(mappedBy = "catalog")
    private List<ProductCatalog> productCatalogs;
}
