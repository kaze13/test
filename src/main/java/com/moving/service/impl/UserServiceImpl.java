package com.moving.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moving.dao.UserDao;
import com.moving.dto.UserDto;
import com.moving.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao dao;

	@Override
	public UserDto getUser(String mail, String password) {
		UserDto dto = dao.getUser(mail, password);
		return dto;
	}

	@Override
	public UserDto addUser(String userId, String password, String email) {

		if(dao.addUser(userId, password, email)){
			return this.getUser(email, password);
		}
		
		return null;
	}

	@Override
	public boolean hasUser(String mail) {
		return dao.hasUser(mail);
	}

	
}
