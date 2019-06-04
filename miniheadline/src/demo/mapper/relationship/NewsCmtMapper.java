package demo.mapper.relationship;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import demo.domain.relationship.*;

public class NewsCmtMapper implements RowMapper<NewsCmt> {
   public NewsCmt mapRow(ResultSet rs, int rowNum) throws SQLException {
	   
	   NewsCmt item = new NewsCmt();
	   
	   item.setId( rs.getInt("ctnid") );
	   item.setCid( rs.getInt("cid") );
	   item.setNid( rs.getInt("nid") );
	   
	   return item;
	   
   }
}
