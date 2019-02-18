package net.koreate.service;

import java.util.List;

import net.koreate.vo.BoardVO;

public interface BoardService {
	
	String register(BoardVO board);

	List<BoardVO> listAll();

	BoardVO read(int bno);

	String modify(BoardVO boardvo);

	String remove(int bno);
	
}
