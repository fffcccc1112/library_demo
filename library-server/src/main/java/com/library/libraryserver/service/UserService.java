package com.library.libraryserver.service;

import com.library.libraryserver.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    int addUser(User user);
    Integer deleteUser(Integer id);
    int updateUser(User user);
}
