package net.koreate.sboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.sboard.dao.CommentDao;
import net.koreate.sboard.util.Criteria;
import net.koreate.sboard.util.PageMaker;
import net.koreate.sboard.vo.CommentVO;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Inject
	CommentDao dao;

	@Override
	public void addComment(CommentVO vo) throws Exception {
		System.out.println("addComment : " + vo);
		// dao 입력 요청 처리
		dao.addComment(vo);
		
	}

	@Override
	public List<CommentVO> commentList(int bno) throws Exception {
		return dao.list(bno);
	}

	@Override
	public void modifyComment(CommentVO vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void removeComment(int cno) throws Exception {
		dao.delete(cno);		
	}

	@Override
	public PageMaker getPageMaker(int page, int bno) throws Exception {
		Criteria cri = new Criteria();
		cri.setPage(page);
		
		int commentListCount = dao.count(bno);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(commentListCount);
		
		return pageMaker;
	}

	@Override
	public List<CommentVO> commentListPage(int bno, Criteria cri) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("cri", cri);
		List<CommentVO> list = dao.listPage(map);
		return list;
	}
	
	
}
