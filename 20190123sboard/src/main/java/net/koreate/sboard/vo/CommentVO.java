package net.koreate.sboard.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class CommentVO {
	
	private int cno;			// 댓글 번호
	private int bno;			// 댓글의 게시물 번호
	private String commentText;	// 댓글 내용
	private String commentAuth;	// 작성자
	private Date regdate;		// 작성시간
	private Date updatedate;	// 수정 시간
	
	@Getter 
	@Setter
	private int uno;			// 댓글 작성자
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getCommentAuth() {
		return commentAuth;
	}
	public void setCommentAuth(String commentAuth) {
		this.commentAuth = commentAuth;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	@Override
	public String toString() {
		return "CommentVO [cno=" + cno + ", bno=" + bno + ", commentText=" + commentText + ", commentAuth="
				+ commentAuth + ", regdate=" + regdate + ", updatedate=" + updatedate + "]";
	}

}
