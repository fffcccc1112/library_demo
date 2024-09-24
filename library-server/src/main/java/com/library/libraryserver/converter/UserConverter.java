package com.library.libraryserver.converter;

import com.library.libraryserver.pojo.User;
import com.library.libraryserver.pojo.dto.UserDto;

public class UserConverter {
    public static UserDto ConvertToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserid(user.getUserid());
        userDto.setUsername(user.getUsername());
        return userDto;
    }
    public static User ConvertToUser(UserDto userDto) {
        User user = new User();
        user.setUserid(userDto.getUserid());
        user.setUsername(userDto.getUsername());
        user.setPhone(userDto.getPhone());
        return user;
    }
}
