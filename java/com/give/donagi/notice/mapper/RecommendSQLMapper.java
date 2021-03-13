package com.give.donagi.notice.mapper;

import com.give.donagi.vo.NoticeRecommendVo;

public interface RecommendSQLMapper 
{
public void insertRecommend(NoticeRecommendVo noticeRecommendVo);
	
	public NoticeRecommendVo isRecommendCliked(NoticeRecommendVo noticeRecommendVo);
	
	public int RecommendCount(int bn_no);
	
	public void deleteRecommend(int bnr_no);
}
