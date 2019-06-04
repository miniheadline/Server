package demo.dao;

import java.util.List;

import javax.sql.DataSource;

import demo.domain.News;;

public interface NewsDAO {

	public void setDataSource(DataSource ds);

	public void insert(String url);
	 
	public void delete(Integer id);
	 
	public News getNews(Integer id);
	  
	public List<News> listNews();
	   
	public void update(Integer id, String url);
	
}
