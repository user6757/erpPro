package com.jin.erp.member.service;

import java.security.MessageDigest;

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
		int result = memberDAO.idcheck(account);
		System.out.println("°ª:"+ result);
		return result;
	}
	
	@Override
	public boolean singup(Member member) throws Exception{
		MessageDigest pw = MessageDigest.getInstance("SHA-512");
        pw.update(member.getPassword().getBytes("UTF-8"));
        byte[] getpassword = pw.digest();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<getpassword.length; i++){
            sb.append(Integer.toString((getpassword[i] & 0xff) + 0x100, 16).substring(1));
        }
        String password = sb.toString();
        member.setPassword(password);
		return memberDAO.singup(member)==1 ? true : false;
	}
	
	@Override
	public Member idsearch(Member member) {
		return memberDAO.idsearch(member);
	}
}
