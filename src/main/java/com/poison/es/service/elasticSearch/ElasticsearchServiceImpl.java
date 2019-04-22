package com.poison.es.service.elasticSearch;

import com.poison.es.domain.Product;
import com.poison.es.domain.ProductGroup;
import com.poison.es.elasticSearch.ProductES;
import com.poison.es.elasticSearch.ProductGroupES;
import com.poison.es.elasticSearch.ProductGroupSearch;
import com.poison.es.elasticSearch.ProductSearch;
import com.poison.es.service.product.ProductMapper;
import com.poison.es.service.product.ProductService;
import com.poison.es.service.productGroup.ProductGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class ElasticsearchServiceImpl implements ElasticsearchService {

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchServiceImpl.class);


    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductSearch productSearch;
    @Autowired
    private ProductGroupMapper productGroupMapper;
    @Autowired
    private ProductGroupSearch productGroupSearch;
    @Autowired
    private ProductService productService;

    @Override
    public void check(){
        //商品
        //全量查出mysql的数据
        List<Product>products=productMapper.findAllShort();
        //遍历去check
        List<ProductES> newProductEss=new ArrayList<>();
        for (Product product:products){
            logger.info("商品id："+product.getId());
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
                    newProductEss.add(productESMySql);
                }
            }catch (Exception e){
                //为空会抛异常，那就说明这条数据丢失了
                newProductEss.add(productESMySql);
            }
        }
        if (newProductEss.size()!=0){
            productSearch.saveAll(newProductEss);
        }
        //group关联表
        //全量查出mysql的数据
        List<ProductGroup>productGroups=productGroupMapper.findAllShort();
        //遍历去check
        List<ProductGroupES>newProductGroupESs =new ArrayList<>();
        for (ProductGroup productGroup:productGroups){
            logger.info("商品id："+productGroup.getProductId()+"分组id："+productGroup.getGroupId());
            ProductGroupES productGroupESMySql=new ProductGroupES();
            productGroupESMySql.setGroupId(String.valueOf(productGroup.getGroupId()));
            productGroupESMySql.setProductId(String.valueOf(productGroup.getProductId()));
            try{
                Object productGroupES=productGroupSearch.findAllByGroupId(productGroupESMySql.getGroupId()).get();
                if (productGroupES.equals(productGroupESMySql)){
                    continue;
                }else {
                    //从新插入
                    newProductGroupESs.add(productGroupESMySql);
                }
            }catch (Exception e){
                //为空会抛异常，那就说明这条数据丢失了
                newProductGroupESs.add(productGroupESMySql);
            }
        }
        if (newProductGroupESs.size()!=0){
            productGroupSearch.saveAll(newProductGroupESs);
        }
    }

    @Override
    public void test(){
        for(int i=0;i<100000;i++){
            List<ProductGroup> productGroups=new ArrayList<>();
            for (int j=0;j<5;j++){
                ProductGroup productGroup=new ProductGroup();
                productGroup.setGroupId(Long.valueOf(j+1));
                productGroup.setPrice(BigDecimal.valueOf(j*i));
                productGroup.setRemark(j+"的价格");
                productGroups.add(productGroup);
            }
            Product product=new Product();
            product.setName("第一类测试商品"+i);
            product.setDescription("第一次测试商品"+i+"的描述");
            product.setTypeId(1L);
            productService.addProduct(product);
            logger.info(product.getName());
        }
    }
}
