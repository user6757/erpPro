package com.jin.erp.member.service;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.erp.member.dao.MemberDAO;
import com.jin.erp.member.domain.Member;

@Service
public class MemberServiceImpl implements MemberService{
	
	private MemberDAO memberDAO;
	
	@Autowired
	public MemberServiceImpl(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public int idcheck(String account) {
		return memberDAO.idcheck(account);
		
	}
	
	@Override
	public boolean singup(Member member) throws Exception{
		if(member == null) {
			System.out.println("없음");
			return false;
		}
		int account = member.getAccount().length();
		if(account < 4 && account < 16) {
			return false;
		}
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
	
	@Override
	public Map<String, Object> passwordfind(String account, String email) {
		Map<String, Object> userpassword = new HashMap<>();
		
		Member memberVO = new Member();
		memberVO.setAccount(account);
		memberVO.setEmail(email);
		int result = memberDAO.passwordfind(memberVO);
		if(result ==1) {
			
			Random random = new Random();
			int password = random.nextInt(1000000);
			memberDAO.setpassword(password);
			String newpassword = Integer.toString(password);
			userpassword.put("message", newpassword);
			return userpassword;
		}else {
			userpassword.put("message", "REG-NO");
			return userpassword;
		}
	}
}
