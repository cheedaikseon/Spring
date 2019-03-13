package net.koreate.sboard.vo;

import java.util.Date;

public class BanIPVO {
	
	private String ip;
	private int cnt;
	private Date bandate;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public Date getBandate() {
		return bandate;
	}
	public void setBandate(Date bandate) {
		this.bandate = bandate;
	}
	@Override
	public String toString() {
		return "BanIPVO [ip=" + ip + ", cnt=" + cnt + ", bandate=" + bandate + "]";
	}
}
