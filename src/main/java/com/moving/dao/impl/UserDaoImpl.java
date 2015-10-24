package com.moving.dao.impl;

import java.sql.ResultSet;
import java.util.List;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;





import com.moving.dao.UserDao;
import com.moving.dto.UserDto;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	protected JdbcTemplate tmpl;
	
	@Override
	public UserDto getUser(String mail, String password) {

		List<UserDto> list  = tmpl.query("SELECT * FROM users where email = ? and "
				+ "password = MD5(?)", new Object[]{mail, password}, this.getRowMapper());
		
		return list == null || list.size() == 0 ? null : list.get(0);
	}

	@Override
	public boolean addUser(String userId, String password, String email) {
		int row = tmpl.update("INSERT INTO users (userId, password, name, email, auth) "
				+ "VALUES (?, MD5(?), '', ?, '1')", new Object[]{userId, password, email});
		
		return row > 0;
	}

	@Override
	public boolean hasUser(String mail) {
		List<UserDto> list = tmpl.query("SELECT * FROM users where email = ?", new Object[] {mail}, this.getRowMapper());
		return list.size() > 0;
	}
	
	public RowMapper<UserDto> getRowMapper(){
		return  (ResultSet rs, int num) -> {
			return UserDto.builder().id(rs.getLong("id"))
					.userId(rs.getString("userId"))
					.name(rs.getString("name"))
					.email(rs.getString("email"))
					.auth(rs.getInt("auth")).build();
		};
	}

}
