package com.ecommerce.kgateway.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AuditModel {
    private Date createDate;
    private Date updateDate;
}
