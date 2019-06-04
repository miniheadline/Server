package demo.mapper.relationship;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import demo.domain.relationship.*;

public class UsersCmtMapper implements RowMapper<UsersCmt> {
   public UsersCmt mapRow(ResultSet rs, int rowNum) throws SQLException {
	   
	   UsersCmt item = new UsersCmt();
	   
	   item.setId( rs.getInt("utcid") );
	   item.setCid( rs.getInt("cid") );
	   item.setUid( rs.getInt("uid") );
	   
	   return item;
	   
   }
}