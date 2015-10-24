package com.moving.service;

import org.springframework.stereotype.Service;

import com.moving.dto.UserDto;

public interface UserService {

	public UserDto getUser(String mail, String password);
	
	public boolean hasUser(String mail);
	
	public UserDto addUser(String userId, String password, String email);
	
}
