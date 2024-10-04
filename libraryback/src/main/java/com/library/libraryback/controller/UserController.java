package com.library.libraryback.controller;

import com.library.libraryback.entity.Result;
import com.library.libraryback.entity.User;
import com.library.libraryback.mapper.UserXmlMapper;
import com.library.libraryback.service.UserService;
import com.library.libraryback.util.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
@Validated
public class UserController {
    @Autowired
    private UserXmlMapper userXmlMapper;
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllUser() {

        return userXmlMapper.getAllUser();
    }
        @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") int id){
            return userService.deleteUserById(id);
    }
    //根据用户名查询
    @GetMapping("/find/{username}")
    public User getUserByUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }
    //根据phone查找用户
    @GetMapping("/phone/{phone}")
    public User getUserByPhone(@PathVariable("phone") String phone){
        return userService.getUserByPhone(phone);
    }
    //增加用户
    @PostMapping("/add")
    public Result addUser(@RequestBody User user) {
        if(userService.getUserByPhone(user.getPhone())!=null){
            return Result.error("电话已被注册");
        }
        else if (userService.getUserByUsername(user.getUsername())!=null) {
            return Result.error("用户名不能重复!");
        }
       else {
                user.setPassword(Md5Util.inputPassToFormPass(user.getPassword()));
                userService.addUser(user);
                return Result.success(user);
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
    public  Result login(@Pattern(regexp = "^\\S{2,16}$") String username,@Pattern(regexp = "^\\S{2,16}$") String password){
        //查找用户名
        if(userService.getUserByUsername(username)!=null){
            User  user1=userService.getUserByUsername(username);
            if(user1.getPassword().equals(Md5Util.inputPassToFormPass(password))) {
                return Result.success(user1);
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