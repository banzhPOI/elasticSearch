package com.poison.es.service.productGroup;

import com.poison.es.domain.ProductGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ProductGroupMapper {

    void addProductGroups(@Param("list") List<ProductGroup> productGroups);

    List<ProductGroup> findAllShort();
}
