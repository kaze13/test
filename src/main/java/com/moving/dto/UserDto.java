package com.moving.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1014232081836268600L;


    private Long id; 
	
	private String userId;
	private String password;
	
	private String email;
	private String name;
	private Integer auth;
	
}
