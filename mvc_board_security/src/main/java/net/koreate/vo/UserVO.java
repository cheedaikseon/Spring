package net.koreate.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserVO {
	
	private int uno;
	private String uid;
	private String upw;
	private String uname;
	
	private Date regDate;
	private Date updateDate;
	
	private List<AuthVO> authList;

}
