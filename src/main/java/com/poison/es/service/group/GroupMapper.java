package com.poison.es.service.group;

import com.poison.es.domain.Group;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupMapper {

    void addGroup(Group group);
}
