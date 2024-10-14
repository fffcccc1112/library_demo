package com.library.libraryback.controller;

import com.library.libraryback.entity.LoginUser;
import com.library.libraryback.entity.Result;
import com.library.libraryback.entity.User;
import com.library.libraryback.service.UserService;
import com.library.libraryback.util.GetExpiredTime;
import com.library.libraryback.util.JwtUtil;
import com.library.libraryback.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RequestMapping("/user")
@RestController
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @Qualifier("resourceHandlerMapping")
    @Autowired
    private HandlerMapping resourceHandlerMapping;

    @GetMapping("/all")
    public List<User> getAllUser() {
        return userService.getUsers();
    }
    /**
     * 根据用户名id查询
     */
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") int id){
            return userService.deleteUserById(id);
    }
    /**
     * 更加响应头的Authorization来解析用户名和用户id
     */
    @GetMapping("/userinfo")
    public Result getUserInfo(@RequestHeader(name="Authorization") String token) {
        Map<String, Object> map = JwtUtil.parseToken(token);
        return Result.success(map);
    }

    /**
     * 根据phone来查找
     */
    @GetMapping("/phone/{phone}")
    public User getUserByPhone(@PathVariable("phone") String phone){
        return userService.getUserByPhone(phone);
    }
    /**
     * 注册功能
     */
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
    /**
     * 根据id来删除
     */
    @DeleteMapping("/delete/{id}")
    public Result<User> deleteUser(@PathVariable("id") int id) {
        User user = userService.deleteUserById(id);
        return Result.success(user);
    }
    /**
     * 登录接口,
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginUser loginUser){
        User  user1=userService.getUserByUsername(loginUser.getUsername());
        if(user1!=null){
            if(user1.getPassword().equals(
                    Md5Util.inputPassToFormPass(loginUser.getPassword()))
            ) {
                Map<String,Object> claims=new HashMap<>();
                claims.put("username",user1.getUsername());
                claims.put("id",user1.getUserid());
                String token = JwtUtil.getToken(claims);
                // TODO: return a expired time
                GetExpiredTime getExpiredTime= new GetExpiredTime();
                return Result.success(token,getExpiredTime.getExpiredtime());
            }
            else {
                return Result.error("密码错误");
            }
        }
        else {
            return Result.error("用户不存在!");
        }
    }
    /**
    *防止过期，重新刷新一下token返回
     */
    @PostMapping("/refresh")
    public Result refreshToken(LocalDateTime repiredTime,String token) {
        //获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 快到期前三十分钟
        TemporalAmount thirtyMinutes = java.time.Duration.ofMinutes(30);
        long timeToExpirationInMinutes = ChronoUnit.MINUTES.between(now, repiredTime);
        // 检查是否快到期（即当前时间与过期时间的差小于或等于30分钟）
        if (timeToExpirationInMinutes <= ((Duration) thirtyMinutes).toMinutes()) {
            // TODO: 返回新令牌
           String newToken = JwtUtil.freshToken(token);
            GetExpiredTime getExpiredTime= new GetExpiredTime();
            return Result.success(newToken,getExpiredTime.getExpiredtime());
        } else {
            // 如果令牌还没有快到期，可以选择不刷新或抛出异常
            throw new IllegalStateException("还未到期");
        }
    }
}