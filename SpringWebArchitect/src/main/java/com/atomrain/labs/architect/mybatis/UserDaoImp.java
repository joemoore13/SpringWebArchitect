package com.atomrain.labs.architect.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.atomrain.labs.architect.domain.User;
import com.atomrain.labs.architect.service.UserDao;

public class UserDaoImp extends SqlSessionDaoSupport implements UserDao {
	
	public User getUserById(long id) {
		return this.getSqlSession().selectOne(
				"com.atomrain.labs.architect.mybatis.UserMapper.getUserById", id);
	}

	public User getUserByUsername(String username) {
		return this.getSqlSession().selectOne(
				"com.atomrain.labs.architect.mybatis.UserMapper.getUserByUsername", username);
	}

	public User getUserByEmail(String email) {
		return this.getSqlSession().selectOne(
				"com.atomrain.labs.architect.mybatis.UserMapper.getUserByEmail", email);
	}
	
	public void createLazyUser(User user) {
		this.getSqlSession().insert(
				"com.atomrain.labs.architect.mybatis.UserMapper.createUser", user);
	}
	
	public User createEagerUser(User user) {
		this.getSqlSession().insert(
				"com.atomrain.labs.architect.mybatis.UserMapper.createUser", user);
		return getUserByUsername(user.getUsername());
	}
	
	public void removeUserById(long id) {
		this.getSqlSession().delete(
				"com.atomrain.labs.architect.mybatis.UserMapper.removeUserById", id);
	}
	
	public void removeUserByUsername(String username) {
		this.getSqlSession().delete(
				"com.atomrain.labs.architect.mybatis.UserMapper.removeUserByUsername", username);
	}
	
	public void removeUserByEmail(String email) {
		this.getSqlSession().delete(
				"com.atomrain.labs.architect.mybatis.UserMapper.removeUserByEmail", email);
	}
}
