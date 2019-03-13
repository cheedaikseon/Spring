package net.koreate.service;

import java.util.List;

import net.koreate.util.Criteria;
import net.koreate.vo.BoardVo;


public interface BoardService {
	public String regist(BoardVo board)throws Exception;
	public BoardVo read(Integer bno)throws Exception;
	public void modify(BoardVo board)throws Exception;
	public void remove(Integer bno)throws Exception;
	
	public List<BoardVo> listCriteria(Criteria cri)throws Exception;
	public int listCountCriteria(Criteria cri);
	
	public void updateCnt(int bno) throws Exception; 
}
