package com.zdq.sm.mapper;

import org.apache.ibatis.annotations.Param;

import com.zdq.sm.model.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
User selectUserById(@Param("name")String name,@Param("password")String password);
int insertUser(User user);
List<Map<String, Object>> getAllUser(Map<String, Object> param);
    List<Map<String, Object>> getEnd_Spot(Map<String, Object> param);
    List<Map<String, Object>> getStart_Spot(Map<String, Object> param);
    List<Map<String, Object>> getPie(Map<String, Object> param);
}
