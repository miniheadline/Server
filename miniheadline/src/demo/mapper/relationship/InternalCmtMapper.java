package demo.mapper.relationship;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import demo.domain.relationship.*;

public class InternalCmtMapper implements RowMapper<InternalCmt> {
   public InternalCmt mapRow(ResultSet rs, int rowNum) throws SQLException {
	   
	   InternalCmt item = new InternalCmt ();
	   
	   item.setId( rs.getInt("ctcid"));
	   item.setCid( rs.getInt("sub_cid") );
	   item.setPid( rs.getInt("obj_cid") );
	   item.setRid( rs.getInt("reply_uid") );
	   
	   return item;
	   
   }
}
