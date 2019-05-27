package com.zdq.sm.service;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

@Service("sqlSessionService")
public class SqlSessionService {
	private static SqlSessionFactory sqlSessionFactory;
	SqlSessionService(){
		try{
			Reader reader=Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		} catch(IOException ignore){
			ignore.printStackTrace();
		}
	}
	
	public SqlSession getSqlSession(){
		return sqlSessionFactory.openSession();
	}
}
