package net.koreate.sboard.vo;

import java.util.Date;

public class MessageVO {
	
	private int mno;			// 메세지 번호
	private String targetid;	// 수신 대상
	private String sender;		// 발신자
	private String message;		// 메세지 내용
	private Date opendate;		// 메세지 읽은 시간
	private Date senddate;		// 발송 시간
	
	// 이하  Getter // Setter toString
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getTargetid() {
		return targetid;
	}
	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getOpendate() {
		return opendate;
	}
	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}
	public Date getSenddate() {
		return senddate;
	}
	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}

	@Override
	public String toString() {
		return "MessageVO [mno=" + mno + ", targetid=" + targetid + ", sender=" + sender + ", message=" + message
				+ ", opendate=" + opendate + ", senddate=" + senddate + "]";
	}

}
