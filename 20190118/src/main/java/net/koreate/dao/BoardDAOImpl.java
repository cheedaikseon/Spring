package net.koreate.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.koreate.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Inject
	SqlSession session;
	
	static String namespace ="net.koreate.BoardMapper";
	
	@Override
	public int register(BoardVO board) {
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

	@Override
	public BoardVO read(int bno) {
		return session.selectOne(namespace+".read",bno);
	}

	@Override
	public int modify(BoardVO boardvo) {
		int result = session.update(namespace+".update",boardvo);
		return result;
		
	}

	@Override
	public int remove(int bno) {
		int result = session.delete(namespace+".remove", bno);
		return result;
	}
	
	

}
