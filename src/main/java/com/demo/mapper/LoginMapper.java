package com.demo.mapper;

import com.demo.model.UserDao;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LoginMapper {
    @Select(
            "SELECT username, password FROM users WHERE username=#{username} AND password=#{password}"
            )
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
    })
    UserDao getUserInfo(@Param("username") String username, @Param("password") String password);
}
