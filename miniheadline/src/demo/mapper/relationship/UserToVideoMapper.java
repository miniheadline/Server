package demo.mapper.relationship;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import demo.domain.relationship.*;

public class UserToVideoMapper implements RowMapper<UserToVideo> {
   public UserToVideo mapRow(ResultSet rs, int rowNum) throws SQLException {
	   
	   UserToVideo item = new UserToVideo();
	   
	   item.setId( rs.getInt("utvid") );
	   item.setCid( rs.getInt("cid") );
	   item.setVid( rs.getInt("vid") );
	   item.setType( rs.getInt("rel_type") );
	   item.setUid( rs.getInt("uid") );
	   
	   return item;
	   
   }
}
