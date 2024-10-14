package com.library.libraryback.entity;
import java.util.Date;

public class Token {
    private String token;
    private long expiredTime;

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime() {
        //获取当前的时间戳
        long startTime = System.currentTimeMillis();
        //过期时间是一个小时
        this.expiredTime = startTime+1000 * 60 * 60;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
