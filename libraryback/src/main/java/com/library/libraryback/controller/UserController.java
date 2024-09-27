package com.library.libraryback.controller;

import com.library.libraryback.entity.User;
import com.library.libraryback.mapper.UserXmlMapper;
import com.library.libraryback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
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
        User user1 =userXmlMapper.getUserById(id);
        return user1;
    }
    //根据用户名查询
    @GetMapping("/find/{username}")
    public User getUserByUsername(@PathVariable("username") String username){
        User user =userXmlMapper.getUserByUsername(username);
        return user;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            userXmlMapper.addUser(user);
            // 假设userXmlMapper.addUser 返回一个插入的行数
            if (userXmlMapper.addUser(user) == 1) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("User added successfully: " + user.getUsername());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Failed to add user");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while adding user: " + e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        try {
            int affectedRows = userXmlMapper.deleteUserById(id);
            if (affectedRows > 0) {
                return ResponseEntity.ok("User deleted successfully: " + id);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting user: " + id);
        }
    }
//    update

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        user.setUserid(id); // 设置用户ID
       try {
           int result = userService.updateUser(user);
           if (result > 0) {
               return ResponseEntity.ok("User updated successfully.");
           } else {
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
           }
       }catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating user: " + id);
       }
    }
    //登录接口
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User user1 =userXmlMapper.getUserByUsername(user.getUsername());
        if (user1 != null && user1.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok("User logged in successfully: " + user.getUsername());
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }
}