package com.jin.erp.member.service;

import com.jin.erp.member.domain.Member;

public interface MemberService {
	
	public int idcheck(String account);
	public boolean singup(Member member);

}
