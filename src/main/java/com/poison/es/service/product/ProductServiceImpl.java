package com.poison.es.service.product;

import com.poison.es.domain.Product;
import com.poison.es.elasticSearch.ProductES;
import com.poison.es.elasticSearch.ProductSearch;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<Product> findAllProducts(String filter) {
        List<Product> products = new ArrayList<>();
        Iterable<ProductES> iterable = productSearch.findByNameLikeOrDescriptionLike(filter, filter);
        List<Long> ids = getIds(iterable);
        if (ids.size() > 0) {
            products = productMapper.findByIds(ids);
        }
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
    public List<Product> findProductsByShopId(Long shopId) {
        QueryBuilder queryBuilder = JoinQueryBuilders.hasChildQuery("shopProduct", QueryBuilders.matchQuery("shopId", shopId), ScoreMode.Max);
        Pageable pageable = PageRequest.of(0, 10);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).withPageable(pageable).build();
        searchQuery.addIndices("es");
        searchQuery.addTypes("product");
        Page<ProductES> page = productSearch.search(searchQuery);
        List<Product>products=makeProductInfo(page);
        //只用es关联查出列表
        return products;
    }

    private List<Product> makeProductInfo(Page<ProductES> page) {
        List<Product>products=new ArrayList<>();
        List<ProductES>productESs=page.getContent();
        List<Long>ids=new ArrayList<>();
        for (ProductES productES:productESs) {
            Long id= Long.valueOf(productES.getId());
            ids.add(id);
        }
        if (ids.size()!=0){
            products=productMapper.findByIds(ids);
        }
        return products;
    }
}
