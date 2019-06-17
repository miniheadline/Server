package demo.service;

import java.util.Collection;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import demo.dao.UserDAO;
import demo.domain.User;
import demo.mapper.UserMapper;


public class UserJDBCTemplate implements UserDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject; 
	
	
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void insert(String name, String password, String pic_url, String description, String address, String birthday) {
		
		String sql = " insert into users(username,password,pic_url,description,birthday,address) values(?,?,?,?,?,?) ";
		jdbcTemplateObject.update( sql, name, password, pic_url, description, birthday, address );
		
		System.out.println("Insert into Table Users with name: " + name);
	}
	 
	public void delete(Integer id) {
		String sql = "delete from Users where uid=?";
	    jdbcTemplateObject.update(sql, id);
	     
	    System.out.println("Deleted Users with ID: " + id );
	}
	 
	public User getUser(Integer id) {
		String sql = "select * from Users where uid = ?";
	    User user = jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new UserMapper());
	    return user;
	}
	  
	public List<User> listUsers() {
		String sql = "select * from Users";
	    List <User> users = jdbcTemplateObject.query(sql, new UserMapper());
	    return users;
	}
	   
	public void update(Integer id, String name, String password, String pic_url, String description, String address, String birthday) {
		String sql = "update Users set username = ? where uid = ?";
	    jdbcTemplateObject.update(sql, name, id);
	    
	    System.out.println("Updated Users with ID = " + id );
	}
	
	public int logIn(String username, String password) {
			
		String sql = "select * from Users where username = ? and password = ?";
		List <User> users = jdbcTemplateObject.query(sql, new Object[]{username, password}, new UserMapper());
	   
	    if (users.size() > 0) {
	    	return users.get(0).getId(); // 登陆成功
	    }
	    else {
	    	sql = "select * from Users where username = ?";
	    	users = jdbcTemplateObject.query(sql, new Object[]{username}, new UserMapper());
	 	   
		    if (users.size() > 0) {
	    		return -1;  // 密码错误
	    	}
	    	else return -2; // 用户不存在
	    }
	}
	
	public int signIn(String username, String password) {
		
		String sql = "select * from Users where username = ?";
		List <User> users = jdbcTemplateObject.query(sql, new Object[]{username}, new UserMapper());
		
		if (users.size() > 0) {
			return 1; // 用户已存在；
		}
		else {
			sql = " insert into Users(username,password) values(?,?) ";
			jdbcTemplateObject.update( sql, username, password);
			
			System.out.println("Insert into Table Users with name: " + username);
			
			return 0; // 用户注册正常；
		}
	}
	
}
