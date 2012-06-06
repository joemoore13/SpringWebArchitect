package com.atomrain.labs.architect.service;



/**
 * @author Brad Nussbaum | brad@atomrain.com | AtomRain
 */
public class UserServiceException extends ServiceException {

	public static final String GET_USER_BY_ID = "userService.getUserById.exception";
	public static final String GET_USER_BY_EMAIL = "userService.getUserByEmail.exception";
	public static final String GET_USER_BY_USERNAME = "userService.getUserByUsername.exception";
	public static final String CREATE_LAZY_USER = "userService.createLazyUser.exception";
	public static final String CREATE_EAGER_USER = "userService.createEagerUser.exception";
	public static final String REMOVE_USER_BY_ID = "userService.removeUserById.exception";
	public static final String REMOVE_USER_BY_USERNAME = "userService.removeUserByUsername.exception";
	public static final String REMOVE_USER_BY_EMAIL = "userService.removeUserByEmail.exception";
	
	/**
	 * Exception may be serialized.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 */
	public UserServiceException() {}
	
	/**
	 * 
	 */
	public UserServiceException(String code) {
		super(code);
	}
	
	/**
	 * 
	 */
	public UserServiceException(String code, Object[] args) {
		super(code, args);
	}
}
