package com.library.libraryback.service;

import com.library.libraryback.entity.User;

public interface UserService {
    int updateUser(User user);
    User getUserByUsername(String username);
    User addUser(User user);
    User deleteUserById(int id);
    User getUserByPhone(String phone);
}
