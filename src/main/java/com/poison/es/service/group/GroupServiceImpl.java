package com.poison.es.service.group;

import com.poison.es.domain.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public void addGroup(Group group) {
        groupMapper.addGroup(group);
    }

    @Override
    public List<Group> findAllGroups() {
        List<Group> groups = groupMapper.findAllGroups();
        return groups;
    }
}
