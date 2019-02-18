package net.koreate.vo;

import java.util.Date;
import java.util.List;

public class MemberVO {
	
	private int uno;
	private String uid;
	private String upw;
	private String uname;
	private Date regdate;
	private Date updatedate;
	
	private List<AuthVO> authList;

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUpw() {
		return upw;
	}

	public void setUpw(String upw) {
		this.upw = upw;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
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

	public List<AuthVO> getAuthList() {
		return authList;
	}

	public void setAuthList(List<AuthVO> authList) {
		this.authList = authList;
	}

	@Override
	public String toString() {
		return "MemerVO [uno=" + uno + ", uid=" + uid + ", upw=" + upw + ", uname=" + uname + ", regdate=" + regdate
				+ ", updatedate=" + updatedate + ", authList=" + authList + "]";
	}
	
	

}
