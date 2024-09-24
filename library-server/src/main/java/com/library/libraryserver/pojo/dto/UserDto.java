package com.library.libraryserver.pojo.dto;

import com.library.libraryserver.pojo.User;

//表示展示给前端的一些对象
public class UserDto extends User {
    private Integer userid;
    private String username;
    private String phone;


    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public Integer getUserid() {
        return userid;
    }

    @Override
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "phone='" + phone + '\'' +
                ", userid=" + userid +
                ", username='" + username + '\'' +
                '}';
    }
}
