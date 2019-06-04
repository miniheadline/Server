package demo.dao;

import java.util.List;
import javax.sql.DataSource;
import demo.domain.User;
import demo.util.Date;

public interface UserDAO {

	 public void setDataSource(DataSource ds);

	 public void insert(String name, String password, String pic_url, String description, String address, String birthday);
	 
	 public void delete(Integer id);
	 
	 public User getUser(Integer id);
	  
	 public List<User> listUsers();
	   
	 public void update(Integer id, String name, String password, String pic_url, String description, String address, String birthday);
	
}
