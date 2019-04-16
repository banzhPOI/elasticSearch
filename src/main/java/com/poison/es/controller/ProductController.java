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

    //获取列表
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response<?> findAllUsers() {
        List<Product> products = productService.findAllProducts();
        return ResponseHelper.createSuccessResponse(products);
    }

    //新增用户
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response<?> addUser(@RequestBody Product product) {
        productService.addUser(product);
        return ResponseHelper.createSuccessResponse();
    }

}