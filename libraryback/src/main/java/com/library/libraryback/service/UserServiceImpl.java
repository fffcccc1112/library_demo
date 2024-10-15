package com.library.libraryback.service;

import com.library.libraryback.entity.User;
import com.library.libraryback.mapper.UserXmlMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserXmlMapper userXmlMapper;


    @Override
    public int updateUser(User user) {
        return userXmlMapper.upDate(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userXmlMapper.getUserByUsername(username);
    }

    @Override
    public Integer addUser(User user) {
        return userXmlMapper.addUser(user);
    }

    @Override
    public List<User> getUsers() {
        return userXmlMapper.getAllUser();
    }

    @Override
    public User deleteUserById(int id) {
        return userXmlMapper.deleteUserById(id);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userXmlMapper.getUserByPhone(phone);
    }

}
