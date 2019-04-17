package com.poison.es.service.product;

import com.google.common.collect.Lists;
import com.poison.es.domain.Product;
import com.poison.es.elasticSearch.ProductES;
import com.poison.es.elasticSearch.ProductSearch;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductSearch productSearch;

    @Override
    public List<Product> findAllProducts() {
        Iterable<ProductES> iterable = productSearch.findAll();
        List<Long> ids = getIds(iterable);
        List<Product> products = productMapper.findByIds(ids);
        return products;
    }

    private List<Long> getIds(Iterable<ProductES> iterable) {
        List<Long> ids = new ArrayList<>();
        Iterator<ProductES> it = iterable.iterator();
        while (it.hasNext()) {
            Long id = Long.valueOf(it.next().getId());
            ids.add(id);
        }
        return ids;
    }

    @Override
    public void addProduct(Product product) {
        productMapper.addProduct(product);
        ProductES productES = makeESInfo(product);
        productSearch.save(productES);
    }

    private ProductES makeESInfo(Product product) {
        ProductES productES = new ProductES();
        productES.setId(String.valueOf(product.getId()));
        productES.setName(product.getName());
        productES.setDescription(product.getDescription());
        return productES;
    }

    @Override
    public List<Product> findProductsByShopId(Long shopId){
        //只用es关联查出列表
        List<Product>products=new ArrayList<>();
        return products;
    }
}
