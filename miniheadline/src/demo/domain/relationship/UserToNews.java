package demo.domain.relationship;

// �û�����Ѷ��ϵ��nid��Ѷ��uid�û���cid���ۣ�rel_type 0:�����1:���ޣ�2:�ղأ�3:����

public class UserToNews {

	private int id;
	private int uid;
	private int nid;
	private int cid;
	private int type;
	
	public int getId() { return this.id; }
	
	public int getUid() { return this.uid; }
	
	public int getNid() { return this.nid; }
	
	public int getCid() { return this.cid; }
	
	public int getType() { return this.type; }
	
	public void setId(int value) { this.id = value; }

	public void setUid(int value) { this.uid = value; }
	
	public void setNid(int value) { this.nid = value; }
	
	public void setCid(int value) { this.cid = value; }
	
	public void setType(int value) { this.type = value; }
    
}
