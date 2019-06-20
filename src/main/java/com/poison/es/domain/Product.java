package com.poison.es.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {

    private Long id;
    private String Code;
    private String name;
    private String description;
    private String keywords;
    private String propertyId;//属性id
    private String propertyName;//属性名
    private String brandId;
    private String brandName;
    private String categoryId;
    private String spec;//包装
    private String dcTemplateId;//小红马商城用
    private String supplyTemplateId;//平台商家用
    private String supplierId;//服务商id
}
