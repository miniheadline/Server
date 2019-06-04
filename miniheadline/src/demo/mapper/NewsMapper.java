package demo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import demo.domain.News;;

public class NewsMapper implements RowMapper<News> {
   public News mapRow(ResultSet rs, int rowNum) throws SQLException {
      
      News news = new News();
      news.setId( rs.getInt("nid") );
      news.setUrl( rs.getString("url"));
      
      return news;
   }
}