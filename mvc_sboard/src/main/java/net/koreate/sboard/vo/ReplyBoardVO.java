package net.koreate.sboard.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//@Data

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude="files")
@EqualsAndHashCode
public class ReplyBoardVO {
	
	private int bno;				// 게시글 번호
	private String title;			// 게시글 제목
	private String content;			// 게시글 내용
	private String writer;			// 게시글 작성자
	private int origin;				// 원본 글 번호(답변의 부모글)
	private int depth;				// 답변에 따른 표현 
	private int seq;				// 답변글에 따른 정렬
	private Date regdate;			// 작성 시간
	private Date updatedate;		// 수정 시간
	private int viewcnt;			// 조회수
	private String showboard;		// 게시물 삭제 여부   y  ,  n
	
	private String[] files;			// 해당 게시물에 저장된 게시물 경로와 이름 목록
	private int uno;				// 작성자 번호
	private int commentCnt;			// 전체 댓글 개수	
	
}
