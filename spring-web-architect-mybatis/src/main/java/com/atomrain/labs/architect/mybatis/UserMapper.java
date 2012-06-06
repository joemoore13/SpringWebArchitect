package com.atomrain.labs.architect.mybatis;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.atomrain.labs.architect.domain.User;

@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM `user` WHERE `id` = #{id}")
	User getUserById(@Param("id") long id);
	
	@Select("SELECT * FROM `user` WHERE `username` = #{username}")
	User getUserByUsername(@Param("username") String username);
	
	@Select("SELECT * FROM `user` WHERE `email` = #{email}")
	User getUserByEmail(@Param("email") String email);
	
	@Insert("INSERT INTO `user` (`username`, `password`, `email`) " +
			"VALUES (#{username}, #{password}, #{email})")
	void createUser(@Param("username") String username, 
					@Param("password") String password, 
					@Param("email") String email);
	
	@Delete("DELETE FROM `user` WHERE `id` = #{id}")
	void removeUserById(@Param("id") long id);
	
	@Delete("DELETE FROM `user` WHERE `username` = #{username}")
	void removeUserByUsername(@Param("username") String username);
	
	@Delete("DELETE FROM `user` WHERE `email` = #{email}")
	void removeUserByEmail(@Param("email") String email);
}