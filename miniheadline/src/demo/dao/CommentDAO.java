package demo.dao;

import java.util.List;

import javax.sql.DataSource;

import demo.domain.Comment;
import demo.util.DateWithSecond;

public interface CommentDAO {

	
	 public void setDataSource(DataSource ds);

	 public void insert(Integer fid, String text, String time, Integer replyNum, Integer likeNum);
	 
	 public void delete(Integer id);
	 
	 public Comment getComment(Integer id);
	  
	 public List<Comment> listComments();
	   
	 public void update(Integer cid, Integer fid, String text, String time, Integer replyNum, Integer likeNum);
	
	
	
}
