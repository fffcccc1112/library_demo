package com.library.libraryback.controller;

import com.library.libraryback.entity.LoginUser;
import com.library.libraryback.entity.Result;
import com.library.libraryback.entity.Token;
import com.library.libraryback.entity.User;
import com.library.libraryback.service.UserService;
import com.library.libraryback.util.GetExpiredTime;
import com.library.libraryback.util.JwtUtil;
import com.library.libraryback.util.Md5Util;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/user")
@RestController
@Validated
@CrossOrigin
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
                Token token=new Token();
                token.setToken(JwtUtil.getToken(claims));
                token.setExpiredTime();
                // TODO: return a expired time
                //将令牌和过期时间存在session里面
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
    /**
    *防止过期，重新刷新一下token返回
     * 直接从响应头拿token@RequestHeader("Authorization")
     */
    @PostMapping("/refresh")
    public Result refreshToken(@RequestHeader("Authorization") String token) {
        // 处理令牌的逻辑
        if (token!= null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Token newToken = new Token();
            newToken.setToken(JwtUtil.freshToken(token));
            newToken.setExpiredTime();
            return Result.success(newToken);
        }
        return Result.error("无效的令牌");
    }




    /**
     * 查看拦截器里 request.setAttribute的效果
     * @param request
     * @return
     */
    @GetMapping("/example")
    public String example(HttpServletRequest request) {
        Object userid = request.getAttribute("userid");
        String username = (String) request.getAttribute("username");
        // 使用 userid 和 username 做一些操作
        return "User ID: " + userid + ", Username: " + username;
    }


}