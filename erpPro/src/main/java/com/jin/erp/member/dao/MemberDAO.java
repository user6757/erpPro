package com.jin.erp.member.dao;

import com.jin.erp.member.domain.Member;

public interface MemberDAO {
	
	public int idcheck(String account);
	public int singup(Member member);
}
