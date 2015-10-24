package com.moving.dto;

import lombok.Data;

@Data
public class CategoryDto  {
	private long id;
	private String name;
	private String dictKey;
	private Long parentId;
}
