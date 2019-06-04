package demo.domain;
import demo.util.*;

public class Comment {
	
	public Integer cid;		// comment id  
	public Integer fromId;  // writer��s id
	public String text; 	// text
	public DateWithSecond time;		// written time
	public Integer replyNum;
	public Integer likeNum;
	
	public void setCid(Integer id) { this.cid = id; }
	
	public void setFid(Integer id) { this.fromId = id; }
	
	public void setText(String text) { this.text = text; }
	
	public void setTime(DateWithSecond time) { this.time = time; }
	
	public void setTime(String msg) { 
		DateWithSecond time = new DateWithSecond();
		time.setWithString(msg);

		this.time = time;
	}
	
	public void setReplyNum(Integer num) { this.replyNum = num; }
	
	public void setLikeNum(Integer num) { this.likeNum = num; }
	
	
	public Integer getCid() { return this.cid; }
	
	public Integer getFid() { return this.fromId; }
	
	public Integer getReplyNum() { return this.replyNum; }
	
	public Integer getLikeNum()  { return this.likeNum; }
	
	public String getText() { return this.text; }
	
	public DateWithSecond getTime() { return this.time; }
	
}
