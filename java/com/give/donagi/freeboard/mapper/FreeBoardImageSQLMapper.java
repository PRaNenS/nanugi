package com.give.donagi.freeboard.mapper;

import java.util.ArrayList;

import com.give.donagi.vo.BFImgLinkVo;

public interface FreeBoardImageSQLMapper {
	
	public int createKey();

	public void uploadImage(BFImgLinkVo vo);
	
	public ArrayList<BFImgLinkVo> selectImage(int bf_no);
	
}
