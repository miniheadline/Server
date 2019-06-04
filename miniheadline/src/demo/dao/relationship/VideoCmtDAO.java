package demo.dao.relationship;

import java.util.List;

import javax.sql.DataSource;

import demo.domain.relationship.*;

public interface VideoCmtDAO {

	public void setDataSource(DataSource ds);

	public void insert(Integer vid, Integer cid);
	 
	public void delete(Integer id);
	 
	public VideoCmt getItem(Integer id);
	  
	public List<VideoCmt> listAll();
	   
	public void update(Integer id, Integer vid, Integer cid);
	
}
