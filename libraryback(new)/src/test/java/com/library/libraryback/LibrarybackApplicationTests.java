package com.library.libraryback;

import com.library.libraryback.entity.User;
import com.library.libraryback.mapper.UserMapper;
import com.library.libraryback.mapper.UserXmlMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LibrarybackApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserXmlMapper userXmlMapper;
    @Test
    void contextLoads() {

    }
}
