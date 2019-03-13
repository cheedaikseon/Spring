package net.koreate.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.koreate.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Inject
	SqlSession session;
	
	String namespace ="net.koreate.Member";
	
	@Override
	public void insertMember(MemberVO vo) {
		session.insert(namespace+".insertMemberVo",vo);
	}

}
