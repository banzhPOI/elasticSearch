package com.poison.es.controller;

import com.poison.es.common.json.Response;
import com.poison.es.common.json.ResponseHelper;
import com.poison.es.domain.User;

import com.poison.es.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/users")
public class UserController {

    @Autowired
    private UserService userService;

    //获取列表
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response<?> findAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseHelper.createSuccessResponse(users);
    }

    //新增用户
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response<?> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseHelper.createSuccessResponse();
    }

}
