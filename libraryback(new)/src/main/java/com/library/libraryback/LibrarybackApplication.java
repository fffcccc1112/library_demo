package com.library.libraryback;

import com.library.libraryback.entity.Result;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
@SpringBootApplication
@MapperScan("com.library.libraryback.mapper")
public class LibrarybackApplication {

    public static void main(String[] args) {

        SpringApplication.run(LibrarybackApplication.class, args);
        System.out.println("运行成功...");
        LocalDateTime dateTimeNow = LocalDateTime.now();
        System.out.println("当前日期和时间: " + dateTimeNow);
    }

}
