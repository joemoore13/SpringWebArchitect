SpringWebArchitect
==================

A collection of service layer abstractions, implementations, and tests.

Test context for mybatis is located at src/test/resources/META-INF/test-mybatis.xml.

Tests requires a dataSource and those properties may configured in test context test-mybatis.xml.

Examine the application structure:
	com.atomrain.labs.architect.domain
	com.atomrain.labs.architect.services
	com.atomrain.labs.architect.mybatis

Run the following tests:
	com.atomrain.labs.architect.mybatis.UserDaoImplTest
	com.atomrain.labs.architect.mybatis.UserServiceImplTest