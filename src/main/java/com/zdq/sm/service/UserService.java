package com.zdq.sm.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdq.sm.mapper.UserMapper;
import com.zdq.sm.model.User;

@Service("userService")
public class UserService {
	@Autowired
	SqlSessionService sqlSessionService;
	
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
