package com.poison.es.controller;

import com.poison.es.common.json.Response;
import com.poison.es.common.json.ResponseHelper;
import com.poison.es.domain.Shop;
import com.poison.es.service.shop.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    //新增
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response<?> addShop(@RequestBody Shop shop) {
        shopService.addShop(shop);
        return ResponseHelper.createSuccessResponse();
    }

    //详情
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Response<?> findShopById(@PathVariable Long id) {
        Shop shop = shopService.findShopById(id);
        return ResponseHelper.createSuccessResponse(shop);
    }
}
