package com.poison.es.service.type;

import com.poison.es.domain.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public void addType(Type type) {
        typeMapper.addType(type);
    }

    @Override
    public List<Type> findAllTypes() {
        List<Type> types = typeMapper.findAllTypes();
        return types;
    }
}
