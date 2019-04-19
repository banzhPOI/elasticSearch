package com.poison.es.service.type;

import com.poison.es.domain.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeMapper {

    void addType(Type type);

    List<Type> findAllTypes();
}
