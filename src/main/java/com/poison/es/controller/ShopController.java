package com.poison.es.controller;

import com.poison.es.common.json.Response;
import com.poison.es.common.json.ResponseHelper;
import com.poison.es.domain.Shop;
import com.poison.es.service.shop.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
