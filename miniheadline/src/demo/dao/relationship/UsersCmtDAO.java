package demo.dao.relationship;

import java.util.List;

import javax.sql.DataSource;

import demo.domain.relationship.*;

public interface UsersCmtDAO {

	public void setDataSource(DataSource ds);

	public void insert(Integer uid, Integer cid);
	 
	public void delete(Integer id);
	 
	public UsersCmt getItem(Integer id);
	  
	public List<UsersCmt> listAll();
	   
	public void update(Integer id, Integer uid, Integer cid);
	
}
