package com.jin.erp.mroom.dao;

import java.util.List;

import com.jin.erp.mroom.vo.MroomVO;

public interface MroomDAO {
	
	public int mrsave(MroomVO mroomVO);
	public List<MroomVO> mrview(MroomVO mroomVO);
	public MroomVO mrdetail(int mrNo);
	public int mrModify(MroomVO mroomVO);
	
}
