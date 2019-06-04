package demo.service.relationship;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import demo.domain.relationship.*;
import demo.mapper.relationship.*;
import demo.dao.relationship.*;

public class UserRelationJDBCTemplate implements UserRelationDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject; 
	
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public void insert(Integer fromId, Integer toId) {
		String sql = "insert into UsersToUsers(from_uid,to_uid) values(?,?)";
		jdbcTemplateObject.update( sql, fromId, toId);
		
		System.out.println("Insert into Table UsersToUsers with fromid: " + fromId);
		
	}
	 
	public void delete(Integer id) {
		String sql = "delete from UsersToUsers where utuid=?";
		jdbcTemplateObject.update( sql, id );
		
		System.out.println("Delete into Table UsersToUsers with utuid: " + id);
	}
	 
	public UserRelation getItem(Integer id) {
		String sql = "select * from UsersToUsers where utuid = ?";
		UserRelation item = jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new UserRelationMapper());
		
		return item;
	}
	  
	public List<UserRelation> listAll() {
		String sql = "select * from UsersToUsers";
		List<UserRelation> items = jdbcTemplateObject.query(sql, new UserRelationMapper());
		
		return items;
	}
	   
	public void update(Integer id, Integer fromId, Integer toId) {
		
	}
	
}
