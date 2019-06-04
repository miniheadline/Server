package demo.service.relationship;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import demo.domain.relationship.*;
import demo.mapper.relationship.*;
import demo.dao.relationship.*;

public class UsersCmtJDBCTemplate implements UsersCmtDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject; 
	
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void insert(Integer uid, Integer cid) {
		String sql = "insert into UsersToComments(cid,uid) values(?,?)";
		jdbcTemplateObject.update( sql, cid, uid);
		
		System.out.println("Insert into Table UsersToComments with uid and cid: " + String.valueOf(uid) + " " + String.valueOf(cid) );
	}
	 
	public void delete(Integer id) {
		String sql = "delete from UsersToComments where utcid=?";
	    jdbcTemplateObject.update(sql, id);
	     
	    System.out.println("Deleted UsersToComments with ID: " + id );
	}
	 
	public UsersCmt getItem(Integer id) {
		String sql = "select * from UsersToComments where utcid = ?";
		UsersCmt item = jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new UsersCmtMapper());
		
		return item;
	}
	  
	public List<UsersCmt> listAll() {
		String sql = "select * from UsersToComments";
		List<UsersCmt> items = jdbcTemplateObject.query(sql, new UsersCmtMapper());
		
		return items;
	}
	   
	public void update(Integer id, Integer uid, Integer cid) {
		String sql = "update UsersToComments set uid = ? and cid = ? where utcid = ?";
	    jdbcTemplateObject.update(sql, uid, cid, id);
	    
	    System.out.println("Updated UsersToComments with ID = " + id );
	}
	
}
