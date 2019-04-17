package com.poison.es.controller;

import com.poison.es.common.json.Response;
import com.poison.es.common.json.ResponseHelper;
import com.poison.es.domain.ShopProduct;
import com.poison.es.service.shopProduct.ShopProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/shopProducts")
public class ShopProductController {

    @Autowired
    private ShopProductService shopProductService;

    //新增(应该有两种，一个根据店铺加商品，一个根据商品加店铺)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response<?> addShopProduct(@RequestBody ShopProduct shopProduct) {
        shopProductService.addShopProduct(shopProduct);
        return ResponseHelper.createSuccessResponse();
    }
}
