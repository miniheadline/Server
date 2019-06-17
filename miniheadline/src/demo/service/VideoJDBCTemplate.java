package demo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import demo.dao.VideoDAO;
import demo.domain.Video;
import demo.mapper.VideoMapper;

public class VideoJDBCTemplate implements VideoDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject; 
	
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void insert(String url) {
		String sql = "insert into Videos (url) values(?)";
		jdbcTemplateObject.update( sql, url );
		
		System.out.println("Insert into Table Videos with url: " + url);
	}
	
	public int upload(String url, String info, String title, Integer uid) {
		
		String sql = "insert into Videos(url, title, introdution, from_uid) values(?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplateObject.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
            {
                PreparedStatement ps = jdbcTemplateObject.getDataSource()
                        .getConnection().prepareStatement(sql,new String[]{ "url" ,"title", "introdution", "from_uid" });

                ps.setString(1, url);
                ps.setString(2, title);
                ps.setString(3, info);
                ps.setInt(4, uid);            
                
                return ps;
            }
        }, keyHolder);
		
		return keyHolder.getKey().intValue();
	}
	 
	public void delete(Integer id) {
		String sql = "delete from Videos where vid=?";
	    jdbcTemplateObject.update(sql, id);
	     
	    System.out.println("Deleted Videos with ID: " + id );
	}
	 
	public Video getVideo(Integer id) {
		String sql = "select * from Videos where vid = ?";
	    Video video = jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new VideoMapper());
	    return video;
	}
	  
	public List<Video> listVideos() {
		String sql = "select * from Videos";
	    List <Video> videos = jdbcTemplateObject.query(sql, new VideoMapper());
	    return videos;
	}
	   
	public void update(Integer id, String url) {
		String sql = "update Videos set url = ? where vid = ?";
	    jdbcTemplateObject.update(sql, url, id);
	    
	    System.out.println("Updated Videos with ID = " + id );
	}
	
}
