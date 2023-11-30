package com.jin.erp.member.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.erp.member.dao.MemberDAO;
import com.jin.erp.member.domain.Member;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public int idcheck(String account) {
		return memberDAO.idcheck(account);
	}
	
	public boolean singup(Member member) {
		return memberDAO.singup(member)==1 ? true : false;
	}
}
