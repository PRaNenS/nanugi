package com.give.donagi.freeboard.mapper;

import com.give.donagi.vo.BFLikeVo;

public interface FreeBoardLikeSQLMapper {

	public int createLno();
	
	public void doLike(BFLikeVo bfLikeVo);
	
	public void cancelLike(BFLikeVo bfLikeVo);
	
	public BFLikeVo checkLike(BFLikeVo bfLikeVo);
	
	public int countLike(int bf_no);
	
}
