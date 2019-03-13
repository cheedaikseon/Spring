package net.koreate.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVo {
	Integer bno;
	String title;
	String content;
	String writer;
	Date regdate;
	int viewcnt;
	String uid;
}
