package com.poison.es.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ProductGroup {

    private Long groupId;
    private Long productId;
    private BigDecimal price;
    private String remark;
}
