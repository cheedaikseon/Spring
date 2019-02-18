package net.koreate.dao;

import java.util.List;

import net.koreate.vo.BoardVO;

public interface BoardDAO {
	
	int register(BoardVO boardVo);
	
	List<BoardVO> listAll();

	BoardVO read(int bno);

	int modify(BoardVO boardvo);

	int remove(int bno);
	
}







