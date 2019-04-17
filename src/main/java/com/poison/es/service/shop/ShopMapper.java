package com.poison.es.service.shop;

import com.poison.es.domain.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ShopMapper {

    void addShop(Shop shop);

    Shop findShopById(@Param("id") Long id);
}
