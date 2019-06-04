package demo.dao;

import java.util.List;
import javax.sql.DataSource;
import demo.domain.Video;

public interface VideoDAO {

	public void setDataSource(DataSource ds);

	public void insert(String url);
	 
	public void delete(Integer id);
	 
	public Video getVideo(Integer id);
	  
	public List<Video> listVideos();
	   
	public void update(Integer id, String url);
	
}
