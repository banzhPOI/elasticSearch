package com.poison.es.service.elasticSearch;

import com.poison.es.domain.Group;
import com.poison.es.domain.Product;
import com.poison.es.domain.ProductGroup;
import com.poison.es.elasticSearch.ProductES;
import com.poison.es.elasticSearch.ProductGroupES;
import com.poison.es.elasticSearch.ProductGroupSearch;
import com.poison.es.elasticSearch.ProductSearch;
import com.poison.es.service.group.GroupMapper;
import com.poison.es.service.product.ProductMapper;
import com.poison.es.service.productGroup.ProductGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class ElasticsearchServiceImpl implements ElasticsearchService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductSearch productSearch;
    @Autowired
    private ProductGroupMapper productGroupMapper;
    @Autowired
    private ProductGroupSearch productGroupSearch;

    @Override
    public void check(){
        //商品
        //全量查出mysql的数据
        List<Product>products=productMapper.findAllShort();
        //遍历去check
        for (Product product:products){
            ProductES productESMySql=new ProductES();
            productESMySql.setId(String.valueOf(product.getId()));
            productESMySql.setName(product.getName());
            productESMySql.setDescription(product.getDescription());
            try{
                ProductES productES=productSearch.findById(productESMySql.getId()).get();
                if (productES.equals(productESMySql)){
                    continue;
                }else {
                    //从新插入
                    productSearch.save(productESMySql);
                }
            }catch (Exception e){
                //为空会抛异常，那就说明这条数据丢失了
                productSearch.save(productESMySql);
            }
        }
        //group关联表
        //全量查出mysql的数据
        List<ProductGroup>productGroups=productGroupMapper.findAllShort();
        //遍历去check
        for (ProductGroup productGroup:productGroups){
            ProductGroupES productGroupESMySql=new ProductGroupES();
            productGroupESMySql.setGroupId(String.valueOf(productGroup.getGroupId()));
            productGroupESMySql.setProductId(String.valueOf(productGroup.getProductId()));
            try{
                Object productGroupES=productGroupSearch.findAllByGroupId(productGroupESMySql.getGroupId()).get();
                if (productGroupES.equals(productGroupESMySql)){
                    continue;
                }else {
                    //从新插入
                    productGroupSearch.save(productGroupESMySql);
                }
            }catch (Exception e){
                //为空会抛异常，那就说明这条数据丢失了
                productGroupSearch.save(productGroupESMySql);
            }
        }

    }
}
