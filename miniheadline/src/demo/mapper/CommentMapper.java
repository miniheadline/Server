package demo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import demo.domain.Comment;

public class CommentMapper implements RowMapper<Comment> {
   public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
	   
      Comment comment = new Comment();

      comment.setCid( rs.getInt("cid") );
      comment.setFid( rs.getInt("from_uid"));
      comment.setTime( rs.getDate("time").toString() );
      comment.setReplyNum( rs.getInt("reply_num"));
      comment.setLikeNum( rs.getInt("like_num"));
      comment.setText( rs.getString("text"));
      
      return comment;
   }
}