package com.library.libraryback.controller;

import com.library.libraryback.entity.Result;
import com.library.libraryback.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

//@RequestMapping("/localimage")
@RestController


public class FileController {
    @Autowired
    private BookService bookService;
    /**
     * 更具图书bookid,查找封面对应的映射url
     */
    @GetMapping("/findcover/{id}")
    public Result find(@PathVariable("id") int id) {
        return Result.success(
            bookService.findUrl(id)
        );
    }

    /**
     * 由前端传入 对应的文件
     * 参考博客https://blog.csdn.net/weixin_43760328/article/details/107559999
     */
    @RequestMapping("/fileUplode")
    public Result fileUplode(MultipartFile file) {
        // 检查文件是否为空
        if (file.isEmpty()) {
            return Result.error("上传的文件为空");
        }

        try {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 生成唯一的文件名，防止文件名冲突
            String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;

            // 设置文件存储路径
            String uploadDir = "uplode";
            File uploadFolder = new File(uploadDir);
            // 确保目录存在
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            // 定义文件的完整路径
            Path filePath = Paths.get(uploadDir + uniqueFileName);
            // 将上传的文件保存到服务器
            file.transferTo(filePath.toFile());

            return Result.success(uniqueFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }
}
