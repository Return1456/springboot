package com.zdq.sm.mapper;

import org.apache.ibatis.annotations.Param;

import com.zdq.sm.model.User;

public interface UserMapper {
User selectUserById(@Param("name")String name,@Param("password")String password);

int insertUser(User user);
}
