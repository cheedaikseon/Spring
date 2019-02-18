package net.koreate.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.koreate.vo.MemberVo;

@Repository
public class MemberDaoImple implements MemberDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace ="net.koreate.mappers.MemberMapper";
	
	
	@Override
	public void insertMember(MemberVo memberVo) {
		session.insert(namespace +".insertMember",memberVo);
	}
	@Override
	public MemberVo readMember(String userid) {
		MemberVo member = (MemberVo)session.selectOne(namespace+".readMember",userid);
		return member;
	}

	@Override
	public MemberVo readMemberWithPass(String userid, String pass) {
		
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("userid", userid);
		paramMap.put("userpw", pass);
		
		return session.selectOne(namespace+".readWithPass",paramMap);
		
	};

	@Override
	public List<MemberVo> readMemberList() {
		return session.selectList(namespace+".readMemberList");
	}
}
