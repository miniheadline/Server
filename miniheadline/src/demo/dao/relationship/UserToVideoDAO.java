package demo.dao.relationship;

import java.util.List;

import javax.sql.DataSource;

import demo.domain.relationship.*;

public interface UserToVideoDAO {
	
	public void setDataSource(DataSource ds);

	public void insert(Integer vid, Integer cid, Integer uid, Integer type);
	 
	public void delete(Integer id);
	 
	public UserToVideo getItem(Integer id);
	  
	public List<UserToVideo> listAll();
	   
	public void update(Integer id, Integer vid, Integer cid, Integer uid, Integer type);
}
