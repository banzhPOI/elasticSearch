package com.poison.es.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Product {

    private Long id;

    private String code;

    private String name;

    private Long categoryId;

    private Long brandId;

    private String brandName;

    private Long propertyId;//属性id

    private String propertyName;//属性名

    private String spec;//包装

    private String description;

    private String keywords;

    private Integer serviceType;//0:平台商家,1:服务商,2:小红马商城

    private List<Long> dcTemplateId;//小红马商城用

    private List<Long> supplyTemplateId;//平台商家用

    private List<Long> serviceSupplierId;//服务商id
}
