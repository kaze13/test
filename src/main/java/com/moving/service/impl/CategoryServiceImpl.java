package com.moving.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moving.dao.CategoryDao;
import com.moving.dto.CategoryDto;
import com.moving.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao dao;

	@Override
	public CategoryDto getCategory(long id) {
		return dao.getById(id);
	}

}
