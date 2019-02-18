package net.koreate.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.koreate.util.Criteria;
import net.koreate.util.SearchCriteria;
import net.koreate.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Inject
	SqlSession session;
	
	static String namespace ="net.koreate.BoardMapper";
	
	@Override
	public int create(BoardVO board) {
		int result = session.insert(namespace+".create",board);
		System.out.println("result : " + result);
		return result;
	}
	
	@Override
	public List<BoardVO> listAll(){
		/*List<BoardVO> boardList = null;
		boardList = session.selectList(namespace+".listAll");
		return boardList;*/
		return session.selectList(namespace+".listAll");
	}
	
	
	public BoardVO read(int bno) {
		return session.selectOne(namespace+".read",bno);
	}
	
	public int update(BoardVO boardVo) {
		int result = session.update(namespace+".update",boardVo);
		System.out.println("수정 결과 : " + result);
		return result;
	}
	
	public int remove(int bno) {
		int result = session.delete(namespace+".remove",bno);
		System.out.println("삭제 결과 : " + result);
		return result;
	}
	
	public List<BoardVO> listCri(Criteria cri){
/*		int page = 1;
		int perPageNum =10;
		int pageStart = (page-1)*perPageNum;
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("pageStart", pageStart);
		map.put("perPageNum", perPageNum);*/
		return session.selectList(namespace+".listCri",cri);
	}
	
	public int totalCount() {
		return session.selectOne(namespace+".totalCount");
	}

	
	public void updateViewCnt(int bno) {
		session.update(namespace+".updateViewCnt",bno);
	}
	
	public int searchListCount(SearchCriteria cri) {
		return session.selectOne(namespace+".searchListCount",cri);
	}
	
	public List<BoardVO> searchList(SearchCriteria cri){
		return session.selectList(namespace+".listSearch",cri);
	}
	
}
