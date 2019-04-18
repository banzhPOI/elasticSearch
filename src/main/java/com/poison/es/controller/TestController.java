package com.poison.es.controller;

import com.poison.es.Application;
import com.poison.es.domain.Product;
import com.poison.es.elasticSearch.ProductES;
import com.poison.es.elasticSearch.ProductSearch;
import com.poison.es.service.product.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class TestController {

    @Autowired
    private ProductSearch productSearch;
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void Test1() {
        for(int i=0; i<100000; i++) {
            ProductES productES = new ProductES();
            productES.setId(i+"TEST2");
            productES.setName("测试商品");
            productES.setDescription("测试商品描述");
            System.out.println(i);
            productSearch.save(productES);
        }
    }

    @Test
    public void Test2(){
        for(int i=0; i<100000; i++) {
            Product product = new Product();
            product.setName("测试商品");
            product.setDescription("测试商品描述");
            System.out.println(i);
            productMapper.addProduct(product);
        }
    }

}
