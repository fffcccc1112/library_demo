package com.library.libraryback;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.library.libraryback.entity.User;
import com.library.libraryback.mapper.UserMapper;
import com.library.libraryback.mapper.UserXmlMapper;
import com.library.libraryback.util.JwtUtil;
import org.apache.catalina.AccessLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.library.libraryback.util.JwtUtil.parseToken;

@SpringBootTest
class LibrarybackApplicationTests {
    @Test
    void contextLoads() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","admin");
        String token = JwtUtil.getToken(claims);
        //方法二
        System.out.println(parseToken(token));
    }
}
