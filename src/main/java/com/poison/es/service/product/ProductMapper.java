package com.poison.es.service.product;

import com.poison.es.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> findAll();

    void addProduct(Product product);

    List<Product> findByIds(@Param("list") List<Long> ids);
}
