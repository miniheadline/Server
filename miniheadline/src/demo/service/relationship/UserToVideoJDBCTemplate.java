package demo.service.relationship;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import demo.domain.relationship.*;
import demo.mapper.relationship.*;
import demo.dao.relationship.*;

public class UserToVideoJDBCTemplate implements UserToVideoDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject; 
	
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void insert(Integer vid, Integer cid, Integer uid, Integer type) {
		String sql = "insert into UsersToVideos(vid,uid,cid,rel_type) values(?,?,?,?)";
		jdbcTemplateObject.update( sql, vid, uid, cid, type);
		
		System.out.println("Insert into Table UsersToNews with uid: " + String.valueOf(uid) );
	}
	 
	public void delete(Integer id) {
		String sql = "delete from UsersToVideos where utvid=?";
	    jdbcTemplateObject.update(sql, id);
	     
	    System.out.println("Deleted UsersToVideos with ID: " + id );
	}
	 
	public UserToVideo getItem(Integer id) {
		String sql = "select * from UsersToVideos where utvid = ?";
		UserToVideo item = jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new UserToVideoMapper());
		
		return item;
	}
	  
	public List<UserToVideo> listAll() {
		String sql = "select * from UserToVideos";
		List<UserToVideo> items = jdbcTemplateObject.query(sql, new UserToVideoMapper());
		
		return items;
	}
	   
	public void update(Integer id, Integer vid, Integer cid, Integer uid, Integer type) {

	}
	
	public void hasRead(Integer uid, Integer vid, Integer type) {
		String sql = "select * from UsersToVideos where uid = ? and vid = ? and rel_type = ?";
		List<UserToVideo> items = jdbcTemplateObject.query(sql, new Object[]{uid, vid, type}, new UserToVideoMapper());
		
		System.out.println( items.size() );
		
		if (items.size() == 0) {
			insert(vid, 1, uid, type);
		}
	}
	
	public void inverse(Integer uid, Integer vid, Integer type) {
		String sql = "select * from UsersToVideos where uid = ? and vid = ? and rel_type = ?";
		List<UserToVideo> items = jdbcTemplateObject.query(sql, new Object[]{uid, vid, type}, new UserToVideoMapper());
		
		System.out.println( "size: " + items.size() );
		
		System.out.print("success.");
		if (items.size() == 0) {
			insert(vid, 1, uid, type);
			
			System.out.print("success.");
		}
		else if (items.size() > 0) {
			sql = "delete from UsersToVideos where uid = ? and vid = ? and rel_type = ?";
		    jdbcTemplateObject.update(sql, uid, vid, type);
		}
	}
	
	public boolean isConnect(Integer uid, Integer vid, Integer type) {
		String sql = "select * from UsersToVideos where uid = ? and vid = ? and rel_type = ?";
		List<UserToVideo> items = jdbcTemplateObject.query(sql, new Object[]{uid, vid, type}, new UserToVideoMapper());
		
		return (items.size()!=0);
	}
	
}
