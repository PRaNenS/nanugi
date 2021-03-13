package com.give.donagi.reviewFunding.mapper;

import java.util.ArrayList;

import com.give.donagi.vo.CommentRFVo;

public interface UserCommentSQLMapper {
	
	
		
	public void insert(CommentRFVo commentRFVo);
	
	public ArrayList<CommentRFVo> selectByCommentNo(int rf_no);
	
	public void reviewDelete(int crf_no);
	
	public int reviewCount(int rf_no);

	public int createCRFNo();

	
}
