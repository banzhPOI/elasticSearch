package com.poison.es.service.product;

import com.poison.es.domain.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> findAllProducts();
}
