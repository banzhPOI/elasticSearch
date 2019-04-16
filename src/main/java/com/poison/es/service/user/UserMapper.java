package com.poison.es.service.user;

import com.poison.es.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAll();

    User findByUsername(@Param("username") String username);

    void addUser(User user);
}
