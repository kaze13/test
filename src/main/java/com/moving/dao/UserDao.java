package com.moving.dao;

import com.moving.dto.UserDto;

public interface UserDao {

	public UserDto getUser(String userId, String password);
	
	public boolean addUser(String userId, String password, String email);
	
	public boolean hasUser(String mail);
	
}
