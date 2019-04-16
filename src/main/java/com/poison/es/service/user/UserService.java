package com.poison.es.service.user;

import com.poison.es.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    void addUser(User user);
}
