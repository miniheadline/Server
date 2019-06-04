package demo.domain.relationship;

// 用户关注表

public class UserRelation {
	
	private int id;
	private int fromId;
	private int toId;
	
	public void setId(int value) { this.id = value; }
	
	public void setFromId(int value) { this.fromId = value; }
	
	public void setToId(int value) { this.toId = value; }
	
	public int getId() { return id; }
	
	public int getFromId() { return fromId; }
	
	public int getToId() { return toId; }
}
