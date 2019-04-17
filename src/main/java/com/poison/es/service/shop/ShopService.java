package com.poison.es.service.shop;

import com.poison.es.domain.Product;
import com.poison.es.domain.Shop;

import java.util.List;

public interface ShopService {

    void addShop(Shop shop);

    Shop findShopById(Long id);
}
