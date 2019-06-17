package demo.domain;

public class Video {
	
	private Integer id;
	private String url;
	private String info;
	private Integer from_uid;
	private String title;
	
	public void setId(int id) { this.id = id; }
	
	public int getId() { return id; }
	
	public void setUrl(String url) { this.url = url; }
	
	public String getUrl() { return url; }
	
	public void setInfo(String info) { this.info = info; }
	
	public String getInfo() { return info; }
	
	public void setTitle(String title) { this.title = title; }
	
	public String getTitle() { return title; }
	
	public void setUid(int id) { this.from_uid = id; }
	
	public Integer getUid() { return from_uid; }
	
}
