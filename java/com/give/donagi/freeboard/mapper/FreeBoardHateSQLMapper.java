package com.give.donagi.freeboard.mapper;

import com.give.donagi.vo.BFHateVo;

public interface FreeBoardHateSQLMapper {

public int createHno();
	
	public void doHate(BFHateVo bfLikeVo);
	
	public void dontHate(BFHateVo bfLikeVo);
	
	public BFHateVo checkHate(BFHateVo bfLikeVo);
	
	public int countHate(int bf_no);
	
}
