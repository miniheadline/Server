package demo.service.relationship;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import demo.domain.relationship.*;
import demo.mapper.relationship.*;
import demo.dao.relationship.*;

public class UserToNewsJDBCTemplate implements UserToNewsDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject; 
	
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public void insert(Integer nid, Integer cid, Integer uid, Integer type) {
		String sql = "insert into UsersToNews(nid,uid,cid,rel_type) values(?,?,?,?)";
		jdbcTemplateObject.update( sql, nid, uid, cid, type);
		
		System.out.println("Insert into Table UsersToNews with nid: " + String.valueOf(nid) );
	}
	 
	public void delete(Integer id) {
		String sql = "delete from UsersToNews where utnid=?";
	    jdbcTemplateObject.update(sql, id);
	     
	    System.out.println("Deleted UsersToNews with ID: " + id );
	}
	 
	public UserToNews getItem(Integer id) {
		String sql = "select * from UsersToNews where utnid = ?";
		UserToNews item = jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new UserToNewsMapper());
		
		return item;
	}
	  
	public List<UserToNews> listAll() {
		String sql = "select * from UsersToNews";
		List<UserToNews> items = jdbcTemplateObject.query(sql, new UserToNewsMapper());
		
		return items;
	}
	   
	public void update(Integer id, Integer nid, Integer cid, Integer uid, Integer type) {
		
		/*
		 自己实现
		 */
		
	}
	
}
