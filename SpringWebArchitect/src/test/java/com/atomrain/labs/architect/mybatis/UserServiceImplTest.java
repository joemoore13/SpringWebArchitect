package com.atomrain.labs.architect.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import com.atomrain.labs.architect.domain.User;
import com.atomrain.labs.architect.service.UserServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/mybatis-test.xml"})
@Transactional
public class UserServiceImplTest extends AbstractTransactionalTestNGSpringContextTests {
	
	@Autowired(required=true)
	protected UserServiceImpl userService;
	
	@Autowired(required=true)
	protected MessageSource messageSource;
	
	private long id;
	private String username;
	private String password;
	private String email;
	
	private List<UserServiceException> exceptions;
	
	@Before
	public void beforeTest() {
		username = "tester";
		password = "password";
		email = "tester@test.com";
		exceptions = new ArrayList<UserServiceException>();
	}
	
	@After
	public void afterTest() {
		for (UserServiceException e : exceptions) {
			String message = messageSource.getMessage(e.getCode(), e.getArgs(), Locale.getDefault());
			System.out.println(message);
			System.out.println(e.joinStackTrace());
		}
	}

	/**
	 * Satisfies the following services:
	 * 	{@code UserServiceImpl.class.createLazyUser()}
	 * 	{@code UserServiceImpl.class.getUserByEmail()}
	 * 	{@code UserServiceImpl.class.getUserById()}
	 * 	{@code UserServiceImpl.class.removeUserById()}
	 */
	@Test
	public void lazyUserServices() {
		boolean pass = true;
		// satisfies createLazyUser
		try { 
			userService.createLazyUser(username, password, email);
		} catch (UserServiceException e) {
			pass = false;
			exceptions.add(e);
		}
		// satisfies getUserByEmail
		User user;
		try {
			user = userService.getUserByEmail(email);
		} catch (UserServiceException e) {
			pass = false;
			user = new User();
			exceptions.add(e);
		}
		pass = (pass &&
				username.equals(user.getUsername()) &&
			    password.equals(user.getPassword()) &&
			    email.equals(user.getEmail()));
		id = user.getId();
		// satisfies getUserById
		User userById;
		try {
			userById = userService.getUserById(id);
		} catch (UserServiceException e) {
			pass = false;
			userById = new User();
			exceptions.add(e);
		}
		pass = (pass && user.getUsername().equals(userById.getUsername()));
		// satisfies removeUserById
		try {
			userService.removeUserById(id);
		} catch (UserServiceException e) {
			pass = false;
			exceptions.add(e);
		}
		try {
			userById = userService.getUserById(id);
		} catch (UserServiceException e) {
			pass = false;
			userById = new User();
			exceptions.add(e);
		}
		pass = (pass && userById == null); // expect null
		
		Assert.assertTrue(pass);
	}

	/**
	 * Satisfies the following services:
	 * 	{@code UserServiceImpl.class.getEagerUser()}
	 * 	{@code UserServiceImpl.class.getUserByUsername()}
	 * 	{@code UserServiceImpl.class.removeUserByUsername()}
	 * 	{@code UserServiceImpl.class.removeUserByEmail()}
	 */
	@Test
	public void eagerUserServices() {
		boolean pass = true;
		// satisfies getEagerUser
		// satisfies getUserByUsername
		User user;
		try {
			user = userService.createEagerUser(username, password, email);
		} catch (UserServiceException e) {
			pass = false;
			user = new User();
			exceptions.add(e);
		}
		pass = (pass &&
				username.equals(user.getUsername()) &&
			    password.equals(user.getPassword()) &&
			    email.equals(user.getEmail()));
		id = user.getId();
		// satisfies removeUserByUsername
		try {
			userService.removeUserByUsername(user.getUsername());
		} catch (UserServiceException e) {
			pass = false;
			exceptions.add(e);
		}
		User userRemoved = null;
		try {
			userRemoved = userService.getUserById(id);
		} catch (UserServiceException e ) {
			pass = false;
			exceptions.add(e);
		}
		pass = (pass && userRemoved == null); // expect null
		
		// satisfies removeUserByEmail
		try {
			user = userService.createEagerUser(username, password, email);
		} catch (UserServiceException e) {
			pass = false;
			user = new User();
			exceptions.add(e);
		}
		try {
			userService.removeUserByEmail(email);
		} catch (UserServiceException e) {
			pass = false;
			exceptions.add(e);
		}
		try {
			userRemoved = userService.getUserByEmail(email);
		} catch (UserServiceException e) {
			pass = false;
			//expections.add(e);
		}
		pass = (pass && userRemoved == null); // expect null
		
		Assert.assertTrue(pass);
	}
}
