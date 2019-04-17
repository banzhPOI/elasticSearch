package com.poison.es.service.shop;

import com.poison.es.domain.Shop;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ShopMapper {

    void addShop(Shop shop);
}
