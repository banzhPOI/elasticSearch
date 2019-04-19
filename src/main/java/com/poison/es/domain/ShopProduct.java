package com.poison.es.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class ShopProduct {

    private Long shopId;
    private Long productId;
    private BigDecimal price;
}
