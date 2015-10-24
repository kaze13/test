package com.moving.session;

import com.moving.dto.UserDto;

import lombok.Data;

@Data
public class User{

	private String userId;
	private String name;
	private String email;
	private int auth;
	
	
	public static User of(UserDto user){
		User ret = new User();
		ret.setUserId(user.getUserId());
		ret.setEmail(user.getEmail());
		ret.setName(user.getName());
		
		return ret;
	}
	
}
