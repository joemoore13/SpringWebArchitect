package com.atomrain.labs.architect.mybatis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import com.atomrain.labs.architect.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:mybatis-test.xml"})
@Transactional
public class UserDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {
	
	@Autowired(required=true)
	protected UserDaoImp userDao;
	
	private long id;
	private String username;
	private String password;
	private String email;
	
	@Before
	public void beforeTest() {
		username = "tester";
		password = "password";
		email = "tester@test.com";
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
		boolean pass = false;
		User user = new User(username, password, email);
		// satisfies createLazyUser
		userDao.createLazyUser(user);
		// satisfies getUserByEmail
		User lazyUser = userDao.getUserByEmail(email);
		pass = (lazyUser.getUsername().equals(username) &&
				lazyUser.getPassword().equals(password) &&
				lazyUser.getEmail().equals(email));
		id = lazyUser.getId();
		// satisfies getUserById
		User userById = userDao.getUserById(id);
		pass = (pass && user.getUsername().equals(userById.getUsername()));
		// satisfies removeUserById
		userDao.removeUserById(id);
		userById = userDao.getUserById(id);
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
		boolean pass = false;
		User user = new User(username, password, email);
		// satisfies getEagerUser
		// satisfies getUserByUsername
		User eagerUser = userDao.createEagerUser(user);
		pass = (eagerUser.getUsername().equals(username) &&
				eagerUser.getPassword().equals(password) &&
				eagerUser.getEmail().equals(email));
		id = user.getId();
		// satisfies removeUserByUsername
		userDao.removeUserByUsername(user.getUsername());
		User userRemoved = userDao.getUserById(id);
		pass = (pass && userRemoved == null); // expect null
		
		// satisfies removeUserByEmail
		eagerUser = userDao.createEagerUser(user);
		userDao.removeUserByEmail(email);
		userRemoved = userDao.getUserByEmail(email);
		pass = (pass && userRemoved == null); // expect null
		
		Assert.assertTrue(pass);
	}
}
