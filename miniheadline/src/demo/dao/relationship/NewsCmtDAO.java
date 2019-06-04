package demo.dao.relationship;

import java.util.List;

import javax.sql.DataSource;

import demo.domain.relationship.*;

public interface NewsCmtDAO {
	
	public void setDataSource(DataSource ds);

	public void insert(Integer nid, Integer cid);
	 
	public void delete(Integer id);
	 
	public NewsCmt getItem(Integer id);
	  
	public List<NewsCmt> listAll();
	   
	public void update(Integer id, Integer nid, Integer cid);
	
}
