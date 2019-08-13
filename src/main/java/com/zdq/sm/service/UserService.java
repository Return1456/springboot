package com.zdq.sm.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdq.sm.mapper.UserMapper;
import com.zdq.sm.model.User;

import java.util.List;
import java.util.Map;

@Service("userService")
public class UserService {
	@Autowired
	SqlSessionService sqlSessionService;

	public List<Map<String,Object>> getAllUser(Map<String,Object> param) throws Exception {
		SqlSession sqlSession=sqlSessionService.getSqlSession();
		return  sqlSession.getMapper(com.zdq.sm.mapper.UserMapper.class).getAllUser(param);
	}

	public List<Map<String,Object>> getStart_Spot(Map<String,Object> param) throws Exception {
		SqlSession sqlSession=sqlSessionService.getSqlSession();
		return  sqlSession.getMapper(com.zdq.sm.mapper.UserMapper.class).getStart_Spot(param);
	}

	public List<Map<String,Object>> getStart_End(Map<String,Object> param) throws Exception {
		SqlSession sqlSession=sqlSessionService.getSqlSession();
		return  sqlSession.getMapper(com.zdq.sm.mapper.UserMapper.class).getEnd_Spot(param);
	}

	public List<Map<String,Object>> getPie(Map<String,Object> param) throws Exception {
		SqlSession sqlSession=sqlSessionService.getSqlSession();
		return  sqlSession.getMapper(com.zdq.sm.mapper.UserMapper.class).getPie(param);
	}


	public User getUserById(String name,String password){
		SqlSession sqlSession=sqlSessionService.getSqlSession();
		User user;
		try{
			UserMapper userMapper=sqlSession.getMapper(com.zdq.sm.mapper.UserMapper.class);
			user=userMapper.selectUserById(name ,password);
			
		}finally{
			sqlSession.close();
		}
		return user;
	}

	public void addUser(User user) {
		SqlSession sqlSession=sqlSessionService.getSqlSession();
		try{
			UserMapper userMapper=sqlSession.getMapper(com.zdq.sm.mapper.UserMapper.class);
			userMapper.insertUser(user);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
	}
}
