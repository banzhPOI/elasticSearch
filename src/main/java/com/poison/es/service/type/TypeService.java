package com.poison.es.service.type;

import com.poison.es.domain.Type;

import java.util.List;

public interface TypeService {

    void addType(Type type);

    List<Type> findAllTypes();
}
