package com.ecommerce.kgateway.models;

import java.util.Date;
import java.util.UUID;

public class CatalogDetail extends AuditModel{

    private UUID catalogId;
    private String catalogName;
    private String catalogDescription;
    private Integer catalogCapacity;

    public CatalogDetail(Date createDate, Date updateDate, UUID catalogId, String catalogName, String catalogDescription, Integer catalogCapacity) {
        super(createDate, updateDate);
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.catalogDescription = catalogDescription;
        this.catalogCapacity = catalogCapacity;
    }
}
