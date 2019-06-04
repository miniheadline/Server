package demo.mapper.relationship;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import demo.domain.relationship.*;

public class VideoCmtMapper implements RowMapper<VideoCmt> {
   public VideoCmt mapRow(ResultSet rs, int rowNum) throws SQLException {
	   
	   VideoCmt item = new VideoCmt();
	   
	   item.setId( rs.getInt("ctvid") );
	   item.setCid( rs.getInt("cid") );
	   item.setVid( rs.getInt("vid") );

	   return item;
	   
   }
}