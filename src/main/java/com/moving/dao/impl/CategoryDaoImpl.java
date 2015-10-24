package com.moving.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.moving.dao.CategoryDao;
import com.moving.dto.CategoryDto;


@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private JdbcTemplate template;
	private static final String GETBYID = "select * from category where category_id= ?";

	protected RowMapper<CategoryDto> getRowMapper() {
		return new RowMapper<CategoryDto>() {
			@Override
			public CategoryDto mapRow(ResultSet rs, int arg1)
					throws SQLException {
				CategoryDto e = new CategoryDto();
				e.setId(rs.getLong(1));
				e.setName(rs.getString(2));
				e.setDictKey(rs.getString(3));
				e.setParentId(rs.getLong(4));
				return e;
			}
		};
	}

	@Override
	public CategoryDto getById(long id) {
		List<CategoryDto> list = template.query(GETBYID, new Object[] { id },
				this.getRowMapper());
		return list.size() > 0 ? null : list.get(0);
	}

}
