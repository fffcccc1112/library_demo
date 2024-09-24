package com.library.libraryserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
@MapperScan("com.library.libraryserver.mapper")
public class LibraryServerApplication {

    public static void main(String[] args) {
        System.out.println("项目开始启动...");
        SpringApplication.run(LibraryServerApplication.class, args);
        System.out.println("项目启动成功...");
    }
}
