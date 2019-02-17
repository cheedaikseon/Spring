package net.koreate.sboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.koreate.sboard.dao.BoardDao;
import net.koreate.sboard.dao.CommentDao;
import net.koreate.sboard.util.PageMaker;
import net.koreate.sboard.util.SearchCriteria;
import net.koreate.sboard.vo.ReplyBoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	BoardDao dao;
	
	@Inject
	CommentDao commentDao;

	@Override
	public void registReply(ReplyBoardVO boardVo) throws Exception {
		dao.register(boardVo);
		dao.updateOrigin();
		
		String files[] = boardVo.getFiles();
		if(files == null) return;
		for(String fullName : files) {
			// db insert
			dao.addAttach(fullName);
		}
		
		System.out.println("register 작업 완료");
	}

	@Override
	public List<ReplyBoardVO> listReplyCriteria(SearchCriteria cri) throws Exception {
		List<ReplyBoardVO> list = dao.listReplyCriteria(cri);
		for(ReplyBoardVO vo : list) {
			/*
			int commentCnt = commentDao.count(vo.getBno());
			vo.setCommentCnt(commentCnt);
			*/
			vo.setCommentCnt(commentDao.count(vo.getBno()));
		}
		return list;
	}

	@Override
	public void updateCnt(int bno) throws Exception {
		dao.updateCnt(bno);		
	}

	@Override
	public ReplyBoardVO readReply(int bno) throws Exception {
		ReplyBoardVO vo = dao.readReply(bno);
		vo.setCommentCnt(commentDao.count(vo.getBno()));
		return vo;
		//return dao.readReply(bno).setCommentCnt(commentDao.count(bno));
		
	}

	@Transactional
	@Override
	public void replyRegister(ReplyBoardVO boardVo) throws Exception {
		// 답글 등록
		// 기존 글의 seq 값 수정
		System.out.println("boardVo + : " + boardVo);
		dao.updateReply(boardVo);
		
		boardVo.setDepth(boardVo.getDepth()+1);
		boardVo.setSeq(boardVo.getSeq()+1);
		// 답글 작성
		dao.replyRegister(boardVo);
		System.out.println("등록 된 값 : " + boardVo);
		
	}

	@Override
	public void modify(ReplyBoardVO board) throws Exception {
		dao.update(board);
		int bno = board.getBno();
		dao.deleteAttach(bno);
		
		String[] files = board.getFiles();
		if(files == null) {return;}
		for(String fullName : files) {
			Map<String,Object> map = new HashMap<>();
			map.put("bno", bno);
			map.put("fullName", fullName);
			dao.replaceAttach(map);
		}
	}

	@Override
	public void remove(int bno) throws Exception {
		dao.delete(bno);
		dao.deleteAttach(bno);
	}

	@Override
	public PageMaker getPageMaker(SearchCriteria cri) throws Exception {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(dao.listReplyCount(cri));
		return pageMaker;
	}
	
	@Override
	public List<String> getAttach(int bno) throws Exception{
		return dao.getAttach(bno);
	}	
	

}
