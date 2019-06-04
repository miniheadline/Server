package demo.domain.relationship;

// 用户与视频关系表；vid视频；uid用户d；cid评论；rel_type 0:浏览，1:点赞，2:收藏，3:评论

public class UserToVideo {

    private int id;
	private int vid;
	private int uid;
	private int cid;
	private int type;
	
	public int getId() { return this.id; }
	
	public int getVid() { return this.vid; }
	
	public int getUid() { return this.uid; }
	
	public int getCid() { return this.cid; }
	
	public int getType() { return this.type; }
	
	public void setId(int value) { this.id = value; }

	public void setVid(int value) { this.vid = value; }
	
	public void setUid(int value) { this.uid = value; }
	
	public void setCid(int value) { this.cid = value; }
	
	public void setType(int value) { this.type = value; }
}
