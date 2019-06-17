package demo.service;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import demo.dao.CommentDAO;
import demo.domain.Comment;
import demo.mapper.CommentMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class CommentJDBCTemplate implements CommentDAO{

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject; 
	
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public int insert(Integer fid, String text, String time, Integer replyNum, Integer likeNum) {
		String sql = "insert into Comments(text,time,from_uid,reply_num,like_num) values(?,?,?,?,?)";
		
		jdbcTemplateObject.update( sql, text, time, fid, replyNum, likeNum);
		System.out.println("Insert into Table Comments with fid: " + fid);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplateObject.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
            {
                PreparedStatement ps = jdbcTemplateObject.getDataSource()
                        .getConnection().prepareStatement(sql,new String[]{ "text" ,"time", "from_uid", "reply_num", "like_num"});

                ps.setString(1, text);
                ps.setString(2, time);
                ps.setInt(3, fid);
                ps.setInt(4, replyNum);
                ps.setInt(5, likeNum);
                
                return ps;
            }
        }, keyHolder);
		
		return keyHolder.getKey().intValue();
	}
 
	public void delete(Integer id) {
		String sql = "delete from Comments where cid=?";
	    jdbcTemplateObject.update(sql, id);
	     
	    System.out.println("Deleted Comments with ID: " + id );
	}
	 
	public Comment getComment(Integer id) {
		String sql = "select * from Comments where cid = ?";
		
		System.out.println(sql);
		
		Comment comment = jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new CommentMapper());
		
		System.out.println(sql + " " + comment.getCid());
		 
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
