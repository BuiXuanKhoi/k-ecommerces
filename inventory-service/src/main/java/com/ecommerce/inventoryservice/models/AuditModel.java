package com.ecommerce.inventoryservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class AuditModel {
    private Date createDate;
    private Date updateDate;
}
