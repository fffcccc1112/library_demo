package com.library.libraryserver.pojo;

import com.library.libraryserver.pojo.dto.UserDto;
import jakarta.persistence.*;

@Table(name = "user")
@Entity
//映射成数据表
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;
    private String username;
    private String password;
    private String phone;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", userid=" + userid +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
