package demo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import demo.domain.Video;

public class VideoMapper implements RowMapper<Video> {
   public Video mapRow(ResultSet rs, int rowNum) throws SQLException {
      
      Video video = new Video();
      video.setId( rs.getInt("vid") );
      video.setUrl( rs.getString("url"));
      video.setInfo( rs.getString("introdution") );
      video.setTitle( rs.getString("title") );
      video.setUid( rs.getInt("from_uid") );
      
      return video;
   }
}