package com.jin.erp.mroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.erp.mroom.dao.MroomDAO;
import com.jin.erp.mroom.vo.MroomVO;

@Service
public class MroomServiceImpl implements MroomService{

	@Autowired
	private MroomDAO mroomDAO;
	
	public int mroomSave(MroomVO mroomVO) {
		return mroomDAO.mrsave(mroomVO);
	}
	
	public List<MroomVO> mrview(MroomVO mroomVO){
		return mroomDAO.mrview(mroomVO);
	}
	
	public MroomVO mrdetail(int mrNo) {
		return mroomDAO.mrdetail(mrNo);
	}
	
	public int mrModify(MroomVO mroomVO) {
		return mroomDAO.mrModify(mroomVO);
	}
	
	public int mrdelete(MroomVO mroomVO) {
		return mroomDAO.mrDelete(mroomVO);
	}
}
