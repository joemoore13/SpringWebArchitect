SpringWebArchitect
==================

A collection of service layer abstractions, implementations, and tests.

BEFORE BUILDING inspect pom.xml and ensure local repository dependencies exist.

By default your local maven repository lives at

	/Users/your_username/.m2/repository

Remote repositories will be established at a later date.

Test context for mybatis is located at

	src/test/resources/META-INF/test-mybatis.xml

Tests requires a dataSource and those properties may configured in test-mybatis.xml.

Initialize test database by running script:

	src/test/sql/Database.sql
	src/test/sql/User.sql

Examine the application structure:
	
	com.atomrain.labs.architect.domain
	
	com.atomrain.labs.architect.services
	
	com.atomrain.labs.architect.mybatis

Run the service tests:
	
	com.atomrain.labs.architect.mybatis.UserDaoImplTest
	
	com.atomrain.labs.architect.mybatis.UserServiceImplTest