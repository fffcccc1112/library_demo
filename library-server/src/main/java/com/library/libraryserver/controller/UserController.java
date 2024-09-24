package com.library.libraryserver.controller;

import com.library.libraryserver.Response;
import com.library.libraryserver.pojo.User;
import com.library.libraryserver.pojo.dto.UserDto;
import com.library.libraryserver.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController//允许接口方法转为对象，并转化为json
public class UserController {
    //注入
    @Resource
    private UserService userService;
    @PostMapping("/user/add")
    public String addUser(User user) {
      int adduser =  userService.addUser(user);
      return String.valueOf(adduser);
    }
}
