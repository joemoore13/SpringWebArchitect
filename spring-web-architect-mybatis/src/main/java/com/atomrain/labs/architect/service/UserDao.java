package com.atomrain.labs.architect.service;

import com.atomrain.labs.architect.domain.User;

public interface UserDao {
	
	User getUserById(long id);
	
	User getUserByUsername(String username);
	
	User getUserByEmail(String email);
	
	void createLazyUser(User user);
	
	User createEagerUser(User user);

	void removeUserById(long id);

	void removeUserByUsername(String username);

	void removeUserByEmail(String email);
	
}
