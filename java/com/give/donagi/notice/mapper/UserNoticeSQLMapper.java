package com.give.donagi.notice.mapper;

import java.util.ArrayList;

import com.give.donagi.vo.BNoticeVo;

public interface UserNoticeSQLMapper 
{
public int createNNo();
	
	public ArrayList<BNoticeVo> selectAll(int page_num);
	
	public int getPageCount();
	
	public BNoticeVo selectByNo(int no);
	
	public void deleteByBNo(int no); //delete content
	
	public void update(BNoticeVo vo); //update content
	
	public void insert(BNoticeVo vo);
}
