package com.jin.erp.member.service;

import java.util.Map;

import com.jin.erp.member.domain.Member;

public interface MemberService {
	
	public int idcheck(String account);
	public boolean singup(Member member) throws Exception;
	public Member idsearch(Member member);
	public Map<String, Object> passwordfind(String account, String email);

}
