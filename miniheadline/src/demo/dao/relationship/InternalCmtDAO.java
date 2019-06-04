package demo.dao.relationship;

import java.util.List;

import javax.sql.DataSource;
import demo.domain.relationship.*;;

public interface InternalCmtDAO {
	
	public void setDataSource(DataSource ds);

	public void insert(Integer parent_cid, Integer child_cid, Integer reply_uid);
	 
	public void delete(Integer id);
	 
	public InternalCmt getItem(Integer id);
	  
	public List<InternalCmt> listAll();
	   
	public void update(Integer id, Integer parent_cid, Integer child_cid, Integer reply_uid);
	
}
