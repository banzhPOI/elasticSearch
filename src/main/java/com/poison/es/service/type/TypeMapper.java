package com.poison.es.service.type;

import com.poison.es.domain.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TypeMapper {

    void addType(Type type);

    List<Type> findAllTypes();

    Type findTypeById(@Param("id") Long id);
}
