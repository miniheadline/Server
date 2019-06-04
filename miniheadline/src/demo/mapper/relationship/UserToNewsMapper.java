package demo.mapper.relationship;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import demo.domain.relationship.*;

public class UserToNewsMapper implements RowMapper<UserToNews> {
   public UserToNews mapRow(ResultSet rs, int rowNum) throws SQLException {

	   UserToNews item = new UserToNews();
	   
	   item.setId( rs.getInt("utnid") );
	   item.setCid( rs.getInt("cid") );
	   item.setNid( rs.getInt("nid") );
	   item.setType( rs.getInt("rel_type") );
	   item.setUid( rs.getInt("uid") );
	   
	   return item;
   }
}
