package com.jin.erp.mroom.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jin.erp.mroom.vo.MroomVO;

@Repository
public class MroomDAOImpl implements MroomDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	public int mrsave(MroomVO mroomVO) {
		return sqlSession.insert("mrsave", mroomVO);
	}
	
	public List<MroomVO> mrview(MroomVO mroomVO){
		return sqlSession.selectList("mrview", mroomVO);
	}

}
