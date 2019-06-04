package demo.service;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import demo.dao.NewsDAO;
import demo.domain.News;
import demo.mapper.NewsMapper;


public class NewsJDBCTemplate implements NewsDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject; 
	
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void insert(String url) {
		String sql = "insert into News(url) values(?)";
		jdbcTemplateObject.update( sql, url );
		
		System.out.println("Insert into Table News with url: " + url);
	}
	 
	public void delete(Integer id) {
		String sql = "delete from News where nid=?";
	    jdbcTemplateObject.update(sql, id);
	     
	    System.out.println("Deleted News with ID: " + id );
	}
	 
	public News getNews(Integer id) {
		String sql = "select * from News where nid = ?";
	    News news = jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new NewsMapper());
	    return news;
	}
	  
	public List<News> listNews() {
		String sql = "select * from News";
	    List <News> news = jdbcTemplateObject.query(sql, new NewsMapper());
	    return news;
	}
	   
	public void update(Integer id, String url) {
		String sql = "update News set url = ? where nid = ?";
	    jdbcTemplateObject.update(sql, url, id);
	    
	    System.out.println("Updated News with ID = " + id );
	}
	
}
