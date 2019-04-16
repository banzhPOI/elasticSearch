package com.poison.es.service.user;

import com.google.common.collect.Lists;
import com.poison.es.domain.User;
import com.poison.es.elasticSearch.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
//        List<User> users = userMapper.findAll();
        Iterable<User> userIterable =  userRepository.findAll();
        List<User>users= Lists.newArrayList(userIterable);
        return users;
    }

    @Override
    public void addUser(User user){
//        userMapper.addUser(user);
        userRepository.save(user);
    }
}
