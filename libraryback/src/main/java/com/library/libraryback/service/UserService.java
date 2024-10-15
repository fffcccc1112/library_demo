package com.library.libraryback.service;

import com.library.libraryback.entity.User;

import java.util.List;

public interface UserService {
    int updateUser(User user);
    User getUserByUsername(String username);
    Integer addUser(User user);
    List<User> getUsers();
    User deleteUserById(int id);
    User getUserByPhone(String phone);

}
