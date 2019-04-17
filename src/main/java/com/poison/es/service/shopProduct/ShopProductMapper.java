package com.poison.es.service.shopProduct;

import com.poison.es.domain.ShopProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ShopProductMapper {

    void addShopProduct(ShopProduct shopProduct);
}
