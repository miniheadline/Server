package demo.service.relationship;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import demo.domain.relationship.*;
import demo.mapper.relationship.*;
import demo.dao.relationship.*;

public class NewsCmtJDBCTemplate implements NewsCmtDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject; 
	
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public void insert(Integer nid, Integer cid) {
		String sql = "insert into CommentsToNews(cid,nid) values(?,?)";
		jdbcTemplateObject.update( sql, cid, nid);
		
		System.out.println("Insert into Table CommentsToNews with nid and cid: " + String.valueOf(nid) + " " + String.valueOf(cid) );
	}
	 
	public void delete(Integer id) {
		String sql = "delete from CommentsToNews where ctnid=?";
	    jdbcTemplateObject.update(sql, id);
	     
	    System.out.println("Deleted CommentsToNews with ID: " + id );
	}
	 
	public NewsCmt getItem(Integer id) {
		String sql = "select * from CommentsToNews where ctnid = ?";
		NewsCmt item = jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new NewsCmtMapper());
		
		return item;
	}
	  
	public List<NewsCmt> listAll() {
		String sql = "select * from CommentsToNews";
		List<NewsCmt> items = jdbcTemplateObject.query(sql, new NewsCmtMapper());
		
		return items;
	}
	   
	public void update(Integer id, Integer nid, Integer cid) {
		String sql = "update CommentsToNews set nid = ? and cid = ? where ctnid = ?";
	    jdbcTemplateObject.update(sql, nid, cid, id);
	    
	    System.out.println("Updated CommentsToNews with ID = " + id );
	}
	
}
