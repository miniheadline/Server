package demo.dao.relationship;

import java.util.List;

import javax.sql.DataSource;
import demo.domain.relationship.*;;

public interface UserRelationDAO {
	
	public void setDataSource(DataSource ds);

	public void insert(Integer fromId, Integer toId);
	 
	public void delete(Integer id);
	 
	public UserRelation getItem(Integer id);
	  
	public List<UserRelation> listAll();
	   
	public void update(Integer id, Integer fromId, Integer toId);
}
