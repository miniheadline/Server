package demo.service;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import demo.dao.CommentDAO;
import demo.domain.Comment;
import demo.mapper.CommentMapper;

public class CommentJDBCTemplate implements CommentDAO{

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject; 
	
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void insert(Integer fid, String text, String time, Integer replyNum, Integer likeNum) {
		String sql = "insert into Comments(text,time,from_uid,reply_num,like_num) values(?,?,?,?,?)";
		jdbcTemplateObject.update( sql, text, time, fid, replyNum, likeNum);
		
		System.out.println("Insert into Table Comments with fid: " + fid);
	}
 
	public void delete(Integer id) {
		String sql = "delete from Comments where cid=?";
	    jdbcTemplateObject.update(sql, id);
	     
	    System.out.println("Deleted Comments with ID: " + id );
	}
	 
	public Comment getComment(Integer id) {
		String sql = "select * from Comments where cid = ?";
		Comment comment = jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new CommentMapper());
		
		return comment;
	}
	  
	public List<Comment> listComments() {
		String sql = "select * from Comments";
		List<Comment> comment = jdbcTemplateObject.query(sql, new CommentMapper());
		
		return comment;
	}
	   
	public void update(Integer cid, Integer fid, String text, String time, Integer replyNum, Integer likeNum) {
		
		String sql = "update Comments set text = ? where cid = ?";
	    jdbcTemplateObject.update(sql, text, cid);
	    
	    System.out.println("Updated Comments with ID = " + cid );
	}
	
}
