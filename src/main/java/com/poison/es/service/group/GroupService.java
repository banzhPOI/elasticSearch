package com.poison.es.service.group;

import com.poison.es.domain.Group;

import java.util.List;

public interface GroupService {

    void addGroup(Group group);

    List<Group> findAllGroups();
}
