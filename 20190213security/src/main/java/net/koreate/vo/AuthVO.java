package net.koreate.vo;

public class AuthVO {
	
	private String uid;
	private String auth;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	@Override
	public String toString() {
		return "AuthVO [uid=" + uid + ", auth=" + auth + "]";
	}
	
}
