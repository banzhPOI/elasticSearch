package com.poison.es.service.elasticSearch;

import com.poison.es.domain.Product;
import com.poison.es.service.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional

public class ElasticsearchServiceImpl implements ElasticsearchService {

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchServiceImpl.class);

    @Autowired
    private ProductService productService;

    @Override
    public void check() {

        //查询出商品数据
        List<Product> products = productService.findAllProducts();
        int a=1;
    }


}
