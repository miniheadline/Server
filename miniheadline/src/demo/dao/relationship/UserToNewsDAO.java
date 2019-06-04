package demo.dao.relationship;

import java.util.List;

import javax.sql.DataSource;

import demo.domain.relationship.*;

public interface UserToNewsDAO {

	public void setDataSource(DataSource ds);

	public void insert(Integer nid, Integer cid, Integer uid, Integer type);
	 
	public void delete(Integer id);
	 
	public UserToNews getItem(Integer id);
	  
	public List<UserToNews> listAll();
	   
	public void update(Integer id, Integer nid, Integer cid, Integer uid, Integer type);
	
}
