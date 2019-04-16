package com.poison.es.service.product;

import com.poison.es.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    void addUser(Product product);
}
