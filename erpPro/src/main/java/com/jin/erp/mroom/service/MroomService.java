package com.jin.erp.mroom.service;



import java.util.List;

import com.jin.erp.mroom.vo.MroomVO;

public interface MroomService {
	
	public int mroomSave(MroomVO mroomVO);
	public List<MroomVO> mrview(MroomVO mroomVO);
	public MroomVO mrdetail(int mrNo);
	public int mrModify(MroomVO mroomVO);

}
