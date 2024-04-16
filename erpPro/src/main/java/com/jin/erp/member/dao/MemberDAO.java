package com.jin.erp.member.dao;

import java.sql.SQLException;

import com.jin.erp.member.domain.Member;

public interface MemberDAO {
	
	public int idcheck(String account);
	public int singup(Member member) throws SQLException;
	public Member idsearch(Member member);
}
