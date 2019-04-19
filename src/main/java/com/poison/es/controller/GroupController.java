package com.poison.es.controller;

import com.poison.es.common.json.Response;
import com.poison.es.common.json.ResponseHelper;
import com.poison.es.domain.Group;
import com.poison.es.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    //新增
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response<?> addGroup(@RequestBody Group group) {
        groupService.addGroup(group);
        return ResponseHelper.createSuccessResponse();
    }

}
