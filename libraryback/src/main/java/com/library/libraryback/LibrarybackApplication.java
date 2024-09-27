package com.library.libraryback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.library.libraryback.mapper")
public class LibrarybackApplication {

    public static void main(String[] args) {

        SpringApplication.run(LibrarybackApplication.class, args);
        System.out.println("运行成功...");
    }

}
