package com.example.springbootjdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.springbootjdbc.domain.User;

@Repository
public class UserDaoImpl implements UserDao{

	private final String INSERT_SQL = "INSERT INTO USERS(name, address, email)  values(:name,:address,:email)";
	private final String FETCH_SQL = "select record_id, name, address, email from users";
	private final String FETCH_SQL_BY_ID = "select * from users where record_id = :id";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public User create(final User user) {
		KeyHolder holder = new GeneratedKeyHolder();
			SqlParameterSource parameters = new MapSqlParameterSource()
					.addValue("name", user.getName())
					.addValue("address", user.getAddress())
					.addValue("email", user.getEmail());
			namedParameterJdbcTemplate.update(INSERT_SQL, parameters, holder);
			user.setId(holder.getKey().intValue());
			return user;
		}
	
	public List<User> findAll() {
		return namedParameterJdbcTemplate.query(FETCH_SQL, new UserMapper());
	}

	public User findUserById(int id) {
		Map<String, Integer> parameters = new HashMap<String, Integer>();
		parameters.put("id", id);
		return (User) namedParameterJdbcTemplate.queryForObject(FETCH_SQL_BY_ID, parameters, new UserMapper());
	}

}

class UserMapper implements RowMapper {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("record_id"));
		user.setName(rs.getString("name"));
		user.setAddress(rs.getString("address"));
		user.setEmail(rs.getString("email"));
		return user;
	}

}