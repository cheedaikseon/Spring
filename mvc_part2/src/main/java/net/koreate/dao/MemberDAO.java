package net.koreate.dao;

import java.util.List;

import net.koreate.vo.MemberVo;

public interface MemberDAO {

	public void insertMember(MemberVo memberVo);
	
	public MemberVo readMember(String userid);
	
	public MemberVo readMemberWithPass(String userid, String pass);
	
	public List<MemberVo> readMemberList();
	}
