package demo.service.relationship;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import demo.domain.relationship.*;
import demo.mapper.relationship.*;
import demo.dao.relationship.*;

public class VideoCmtJDBCTemplate implements VideoCmtDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject; 
	
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public void insert(Integer vid, Integer cid) {
		String sql = "insert into CommentsToVideos(cid,vid) values(?,?)";
		jdbcTemplateObject.update( sql, cid, vid);
		
		System.out.println("Insert into Table CommentsToVideos with vid and cid: " + String.valueOf(vid) + " " + String.valueOf(cid) );
	}
	 
	public void delete(Integer id) {
		String sql = "delete from CommentsToVideos where ctvid=?";
	    jdbcTemplateObject.update(sql, id);
	     
	    System.out.println("Deleted CommentsToVideos with ID: " + id );
	}
	 
	public VideoCmt getItem(Integer id) {
		String sql = "select * from CommentsToVideos where ctnid = ?";
		VideoCmt item = jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new VideoCmtMapper());
		
		return item;
	}
	  
	public List<VideoCmt> listAll() {
		String sql = "select * from CommentsToVideos";
		List<VideoCmt> items = jdbcTemplateObject.query(sql, new VideoCmtMapper());
		
		return items;
	}
	   
	public void update(Integer id, Integer vid, Integer cid) {
		String sql = "update CommentsToVideos set vid = ? and cid = ? where ctvid = ?";
	    jdbcTemplateObject.update(sql, vid, cid, id);
	    
	    System.out.println("Updated CommentsToVideos with ID = " + id );
	}
	
}
