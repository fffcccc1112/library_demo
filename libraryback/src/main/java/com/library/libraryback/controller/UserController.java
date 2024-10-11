package com.library.libraryback.controller;

import com.library.libraryback.entity.LogUser;
import com.library.libraryback.entity.Result;
import com.library.libraryback.entity.User;
import com.library.libraryback.mapper.UserXmlMapper;
import com.library.libraryback.service.UserService;
import com.library.libraryback.util.JwtUtil;
import com.library.libraryback.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
@Validated
public class UserController {
    @Autowired
    private UserXmlMapper userXmlMapper;
    @Autowired
    private UserService userService;
    @Qualifier("resourceHandlerMapping")
    @Autowired
    private HandlerMapping resourceHandlerMapping;

    @GetMapping("/all")
    public List<User> getAllUser() {

        return userXmlMapper.getAllUser();
    }
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") int id){
            return userService.deleteUserById(id);
    }
    //根据用户名查询
    @GetMapping("/userinfo")
    public Result getUserInfo(@RequestHeader(name="Authorization") String token) {

        Map<String, Object> map = JwtUtil.parseToken(token);
        return Result.success(map);
    }

    //根据phone查找用户
    @GetMapping("/phone/{phone}")
    public User getUserByPhone(@PathVariable("phone") String phone){

        return userService.getUserByPhone(phone);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
            if(userService.getUserByUsername(user.getUsername())==null
                    && userService.getUserByPhone(user.getPhone())==null){
               user.setPassword(Md5Util.inputPassToFormPass(user.getPassword()));
               userService.addUser(user);
               return Result.success(user);
            }
            else {
                return Result.error("操作失败");
            }
    }
    //根据id删除用户
    @DeleteMapping("/delete/{id}")
    public Result<User> deleteUser(@PathVariable("id") int id) {
        User user = userService.deleteUserById(id);
        return Result.success(user);
    }
    //登录接口
    @PostMapping("/login")
    public Result login(LogUser logUser){
        //查找用户名
        if(userService.getUserByUsername(logUser.getUsername())!=null){
            User  user1=userService.getUserByUsername(logUser.getUsername());
            if(user1.getPassword().equals(Md5Util.inputPassToFormPass(logUser.getPassword()))) {
                Map<String,Object> claims=new HashMap<>();
                claims.put("username",user1.getUsername());
                claims.put("id",user1.getUserid());
                String token = JwtUtil.getToken(claims);
                return Result.success(token);
            }
            else {
                return Result.error("密码错误");
            }
        }
        else {
            return Result.error("用户不存在!");
        }
    }
    //可能实现修改密码的功能

}