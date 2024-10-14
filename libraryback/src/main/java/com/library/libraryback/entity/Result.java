package com.library.libraryback.entity;

import com.library.libraryback.util.GetExpiredTime;

import java.time.LocalDateTime;
import java.util.Date;

public class Result<T> {
    private Integer code;
    private String message;
    private T data;
    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //快速返回操作成功响应结果
    public static <E> Result<E> success(E data) {
        Result<E> result = new Result<E>();
        result.setCode(0);
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }
    public static  Result error(String message) {
       Result result = new Result();
       result.setCode(1);
       result.setMessage(message);
       return result;
    }
    public static <E> Result<E> success(E data, LocalDateTime date) {
        Result<E> result = new Result<E>();
        result.setCode(0);
        result.setMessage("操作成功");
        result.setData(data);
        result.setDate(date);
        return result;
    }

}
