package com.atomrain.labs.architect.service;

import com.atomrain.labs.architect.domain.User;

/**
 * @author Brad Nussbaum | brad@atomrain.com | AtomRain
 */
public interface UserService {
	
	User getUserById(long id) throws UserServiceException;
	
	User getUserByUsername(String username) throws UserServiceException;
	
	User getUserByEmail(String email) throws UserServiceException;
	
	void createLazyUser(String name, String password, String email) throws UserServiceException;
	
	User createEagerUser(String name, String password, String email) throws UserServiceException;

	void removeUserById(long id) throws UserServiceException;

	void removeUserByUsername(String username) throws UserServiceException;

	void removeUserByEmail(String email) throws UserServiceException;
}
