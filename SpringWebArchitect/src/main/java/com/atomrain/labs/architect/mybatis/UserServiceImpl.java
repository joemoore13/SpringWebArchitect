package com.atomrain.labs.architect.mybatis;

import org.springframework.beans.factory.annotation.Autowired;

import com.atomrain.labs.architect.domain.User;
import com.atomrain.labs.architect.service.UserService;
import com.atomrain.labs.architect.service.UserServiceException;

public class UserServiceImpl implements UserService {
	
	private UserMapper userMapper;
	
	/**
	 * Default constructor.
	 */
	public UserServiceImpl() {}
	
	/**
	 * @param userMapper A UserMapper interface for database access
	 */
	@Autowired(required=true)
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	/**
	 * Get a user by id.
	 */
	public User getUserById(long id) throws UserServiceException {
		try {
			return this.userMapper.getUserById(id);
		} catch (Exception e) {
			throw new UserServiceException(UserServiceException.GET_USER_BY_ID);
		}
	}
	
	/**
	 * Get a user by username.
	 */
	public User getUserByUsername(String username) throws UserServiceException {
		try {
			return userMapper.getUserByUsername(username);
		} catch (Exception e) {
			throw new UserServiceException(UserServiceException.GET_USER_BY_USERNAME);
		}
	}
	
	/**
	 * Get a user by email.
	 */
	public User getUserByEmail(String email) throws UserServiceException {
		try {
			return userMapper.getUserByEmail(email);
		} catch (Exception e) {
			throw new UserServiceException(UserServiceException.GET_USER_BY_EMAIL);
		}
	}

	/**
	 * Create a user lazily i.e. no return of created user.
	 */
	public void createLazyUser(String username, String password, String email) throws UserServiceException {
		try {
			this.userMapper.createUser(username, password, email);
		} catch (Exception e) {
			throw new UserServiceException(UserServiceException.CREATE_LAZY_USER);
		}
	}
	
	/**
	 * Create a user eagerly i.e. return of created user.
	 */
	public User createEagerUser(String username, String password, String email) throws UserServiceException {
		try {
			this.userMapper.createUser(username, password, email);
		} catch (Exception e) {
			throw new UserServiceException(UserServiceException.CREATE_EAGER_USER);
		}
		return getUserByUsername(username);
	}
	
	/**
	 * Remove a user by id.
	 */
	public void removeUserById(long id) throws UserServiceException {
		try {
			this.userMapper.removeUserById(id);
		} catch (Exception e) {
			throw new UserServiceException(UserServiceException.REMOVE_USER_BY_ID, new Object[] {id});
		}
	}
	
	/**
	 * Remove a user by username.
	 */
	public void removeUserByUsername(String username) throws UserServiceException {
		try {
			this.userMapper.removeUserByUsername(username);
		} catch (Exception e) {
			throw new UserServiceException(UserServiceException.REMOVE_USER_BY_USERNAME, new Object[] {username});
		}
	}
	
	/**
	 * Remove a user by email.
	 */
	public void removeUserByEmail(String email) throws UserServiceException {
		try {
			this.userMapper.removeUserByEmail(email);
		} catch (Exception e) {
			throw new UserServiceException(UserServiceException.REMOVE_USER_BY_EMAIL, new Object[] {email});
		}
	}
	
}
