package demo.domain.relationship;

// �û�����Ƶ��ϵ��vid��Ƶ��uid�û�d��cid���ۣ�rel_type 0:�����1:���ޣ�2:�ղأ�3:����

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
