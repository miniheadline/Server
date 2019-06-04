package demo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import demo.domain.User;

public class UserMapper implements RowMapper<User> {
   public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	   
      User user = new User();
      user.setId( rs.getInt("uid") );
      user.setAddress( rs.getString("address"));
      user.setDate( rs.getString("birthday"));
      user.setDesription( rs.getString("description"));
      user.setPassword( rs.getString("password"));
      user.setPicUrl( rs.getString("pic_url"));
      user.setUsername( rs.getString("username"));
      
      return user;
   }
}