package net.koreate.dao;

import java.util.List;

import net.koreate.util.Criteria;
import net.koreate.vo.BoardVo;


public interface BoardDao {
	public int create(BoardVo vo)throws Exception;
	public BoardVo read(Integer bno)throws Exception;
	public void update(BoardVo vo)throws Exception;
	public void delete(Integer bno)throws Exception;
	public List<BoardVo> listAll()throws Exception;

	public List<BoardVo> listPage(int page)throws Exception;
	public List<BoardVo> listCriteria(Criteria cri);
	public int listCountCriteria(Criteria cri);
		
	public void updateCnt(int bno) throws Exception;
	
}
