package com.poison.es.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Product {

    private Long id;
    private String name;
    private String description;
    private List<String> pictures;
    private Long typeId;
    private List<ProductGroup> productGroups;
    private Date creationTime;
    private Integer deleted;
}
