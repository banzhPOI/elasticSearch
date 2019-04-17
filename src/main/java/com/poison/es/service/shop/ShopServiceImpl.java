package com.poison.es.service.shop;

import com.poison.es.domain.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public void addShop(Shop shop) {
        shopMapper.addShop(shop);
    }
}
