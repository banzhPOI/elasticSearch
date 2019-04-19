package com.poison.es.service.group;

import com.poison.es.domain.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupMapper {

    void addGroup(Group group);

    List<Group> findAllGroups();

    Group findGroupById(@Param("id") Long id);
}
