package com.poison.es.test;

import com.poison.es.Application;
import com.poison.es.domain.Product;
import com.poison.es.service.product.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    ProductService productService;

    @Test
    public void test(){
        for(int i=0;i<100000;i++){
            Product product=new Product();
            product.setName("第一次测试商品"+i);
            product.setDescription("第一次测试商品"+i+"的描述");
            productService.addProduct(product);
            logger.info(product.getName());
        }
    }
}
