package demo.domain.relationship;

// 二级评论表

public class InternalCmt {
    
    private int id;
	private int parent_cid;
	private int child_cid;
	private int reply_uid;
	
	public int getId() { return this.id; } 
	
	public int getPid() { return this.parent_cid; } 
	
	public int getCid() { return this.child_cid; } 
	
	public int getRid() { return this.reply_uid; }
	
	public void setId(int value) { this.id = value; }
	
	public void setPid(int value) { this.parent_cid = value; }
	
	public void setCid(int value) { this.child_cid = value; }
	
	public void setRid(int value) { this.reply_uid = value; }
}
