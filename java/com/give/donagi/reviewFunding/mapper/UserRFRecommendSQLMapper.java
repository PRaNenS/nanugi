package com.give.donagi.reviewFunding.mapper;


import com.give.donagi.vo.RecommendRFVo;

public interface UserRFRecommendSQLMapper {
	
	public int createRRFNo();
	
	public void insert(RecommendRFVo recommendRFVo);
	
	public RecommendRFVo selectByNo(RecommendRFVo recommendRFVo);
	
	public void deleteByNo(RecommendRFVo recommendRFVo);
	
	public int likeCount(int rf_no);


}
