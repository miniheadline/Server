package demo.mapper.relationship;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import demo.domain.relationship.*;

public class UserRelationMapper implements RowMapper<UserRelation> {
   public UserRelation mapRow(ResultSet rs, int rowNum) throws SQLException {
	   
	   UserRelation item = new UserRelation();
	   
	   item.setId( rs.getInt("utuid") );
	   item.setFromId( rs.getInt("from_uid") );
	   item.setToId( rs.getInt("to_uid") );
	   
	   return item;
	   
   }
}
