package demo.domain;

import demo.util.*;

public class User {
	
	private Integer id;
	private String username;
	private String password;
	private String pic_url;
	private String description;
	private String address;
	private Date birthday;
	
	public void setId(int id) { this.id = id; }
	
	public Integer getId() { return id; }
	
	public void setUsername(String name) { this.username = name; }
	
	public String getUsername() { return username; }
	
	public void setPassword(String pw) { this.password = pw; }
	
	public String getPassword() { return password; }
	
	public void setPicUrl(String url) { this.pic_url = url; }
	
	public String getPicUrl() { return pic_url; }
	
	public void setDesription(String description) { this.description = description; }
	
	public String getDesription() { return description; }
	
	public void setAddress(String address) { this.address = address; }
	
	public String getAddress() { return address; }
	
	public void setDate(Date date) { this.birthday = date; }
	
	public void setDate(String date) { 
		String[] msg = date.split("-");
		
		Date birthday = new Date( Integer.parseInt(msg[0]), Integer.parseInt(msg[1]), Integer.parseInt(msg[2]) );		
		this.birthday = birthday;
	}
	
	public Date getDate() { return birthday; }
	
	
}
