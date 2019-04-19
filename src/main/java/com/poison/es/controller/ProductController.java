package com.poison.es.controller;

import com.poison.es.common.json.Response;
import com.poison.es.common.json.ResponseHelper;
import com.poison.es.domain.Product;

import com.poison.es.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //新增
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response<?> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseHelper.createSuccessResponse();
    }

    //根据店铺id获取列表
    @RequestMapping(value = "shops/{shopId}", method = RequestMethod.GET)
    public Response<?> findProductsByShopId(@PathVariable Long shopId) {
        List<Product> products = productService.findProductsByShopId(shopId);
        return ResponseHelper.createSuccessResponse(products);
    }
}
