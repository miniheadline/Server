package demo.service.relationship;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import demo.domain.relationship.*;
import demo.mapper.relationship.*;
import demo.dao.relationship.*;


public class InterCmtJDBCTemplate implements InternalCmtDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject; 
	
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void insert(Integer parent_cid, Integer child_cid, Integer reply_uid) {
		String sql = "insert into CommentsToComments(sub_cid,obj_cid,reply_uid) values(?,?,?)";
		jdbcTemplateObject.update( sql, child_cid, parent_cid, reply_uid);
		
		System.out.println("Insert into Table InterCmt.");
	}
	 
	public void delete(Integer id) {
		String sql = "delete from CommentsToComments where ctcid=?";
		jdbcTemplateObject.update( sql, id);
		
		System.out.println("Delete item in Table InterCmt.");
		
	}
	 
	public InternalCmt getItem(Integer id) {
		String sql = "select * from CommentsToComments where ctcid = ?";
		InternalCmt item = jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new InternalCmtMapper());
		
		return item;
	}
	  
	public List<InternalCmt> listAll() {
		String sql = "select * from CommentsToComments";
		List<InternalCmt> items = jdbcTemplateObject.query(sql, new InternalCmtMapper());
		
		return items;
		
	}
	   
	public void update(Integer id, Integer parent_cid, Integer child_cid, Integer reply_uid) {
		String sql = "update CommentsToComments set sub_cid = ? and obj_cid = ? and reply_uid = ? where ctcid = ?";
	    jdbcTemplateObject.update(sql, child_cid, parent_cid, reply_uid, id);
	    
	    System.out.println("Updated Comments with ID = " + id );
	}
	
}
