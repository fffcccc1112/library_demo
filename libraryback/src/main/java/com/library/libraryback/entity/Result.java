package com.library.libraryback.entity;


import java.time.LocalDateTime;

public class Result<T> {
    private Integer code;
    private String message;
    private T data;
//    private LocalDateTime date;

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

}
