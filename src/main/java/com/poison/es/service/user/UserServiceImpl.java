package com.poison.es.service.user;

import com.poison.es.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAllUsers() {
        List<User> users = userMapper.findAll();
        return users;
    }

    @Override
    public void addUser(User user){
        userMapper.addUser(user);
    }
}
