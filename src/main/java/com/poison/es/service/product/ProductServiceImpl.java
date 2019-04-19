package com.poison.es.service.product;

import com.poison.es.common.error.CodeException;
import com.poison.es.domain.Product;
import com.poison.es.domain.ProductGroup;
import com.poison.es.domain.Type;
import com.poison.es.elasticSearch.ProductES;
import com.poison.es.elasticSearch.ProductGroupES;
import com.poison.es.elasticSearch.ProductGroupSearch;
import com.poison.es.elasticSearch.ProductSearch;
import com.poison.es.service.productGroup.ProductGroupMapper;
import com.poison.es.service.type.TypeMapper;
import org.apache.lucene.search.join.ScoreMode;
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
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private ProductGroupMapper productGroupMapper;
    @Autowired
    private ProductGroupSearch productGroupSearch;

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
        //查询分组是否存在
        hasType(product);
        //商品存数据库
        productMapper.addProduct(product);
        //分组商品价格
        saveProductGroup(product);
        //存es
        saveProductES(product);
    }

    private void saveProductES(Product product) {
        ProductES productES = makeProductESInfo(product);
        productSearch.save(productES);
    }

    private void saveProductGroup(Product product) {
        List<ProductGroup> productGroups = product.getProductGroups();
        for (ProductGroup productGroup : productGroups) {
            productGroup.setProductId(product.getId());
        }
        productGroupMapper.addProductGroups(productGroups);
        //存es
        saveProductGroupES(productGroups);
    }

    private void saveProductGroupES(List<ProductGroup> productGroups) {
        List<ProductGroupES> productGroupESs = makeProductGroupESsInfo(productGroups);
        productGroupSearch.saveAll(productGroupESs);
    }

    private List<ProductGroupES> makeProductGroupESsInfo(List<ProductGroup> productGroups) {
        List<ProductGroupES> productGroupESs = new ArrayList<>();
        for (ProductGroup productGroup : productGroups) {
            ProductGroupES productGroupES = new ProductGroupES();
            productGroupES.setProductId(String.valueOf(productGroup.getProductId()));
            productGroupES.setGroupId(String.valueOf(productGroup.getGroupId()));
            productGroupESs.add(productGroupES);
        }
        return productGroupESs;
    }


    private void hasType(Product product) {
        Long typeId = product.getTypeId();
        Type type = typeMapper.findTypeById(typeId);
        if (type == null) {
            throw new CodeException(-1, "品类不存在或已被删除");
        }
    }

    private ProductES makeProductESInfo(Product product) {
        ProductES productES = new ProductES();
        productES.setId(String.valueOf(product.getId()));
        productES.setName(product.getName());
        productES.setDescription(product.getDescription());
        return productES;
    }

    @Override
    public List<Product> findProductsByShopId(Long shopId) {
        QueryBuilder queryBuilder = JoinQueryBuilders.hasChildQuery("productGroup", QueryBuilders.matchQuery("shopId", shopId), ScoreMode.Max);
        Pageable pageable = PageRequest.of(0, 10);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).withPageable(pageable).build();
        searchQuery.addIndices("es");
        searchQuery.addTypes("product");
        Page<ProductES> page = productSearch.search(searchQuery);
        List<Product> products = makeProductInfo(page);
        //只用es关联查出列表
        return products;
    }

    private List<Product> makeProductInfo(Page<ProductES> page) {
        List<Product> products = new ArrayList<>();
        List<ProductES> productESs = page.getContent();
        List<Long> ids = new ArrayList<>();
        for (ProductES productES : productESs) {
            Long id = Long.valueOf(productES.getId());
            ids.add(id);
        }
        if (ids.size() != 0) {
            products = productMapper.findByIds(ids);
        }
        return products;
    }
}
