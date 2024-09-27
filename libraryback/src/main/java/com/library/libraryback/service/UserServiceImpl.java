package com.library.libraryback.service;

import com.library.libraryback.entity.User;
import com.library.libraryback.mapper.UserXmlMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserXmlMapper userXmlMapper;


    @Override
    public int updateUser(User user) {
        return userXmlMapper.upDate(user);
    }
}
