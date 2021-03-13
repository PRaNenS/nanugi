package com.give.donagi.freeboard.mapper;

import java.util.ArrayList;

import com.give.donagi.vo.ReportBFVo;

public interface FreeBoardReportSQLMapper {

	// 게시글 신고
	
	public void insertReport(ReportBFVo vo);
	
	public ArrayList<ReportBFVo> selectReportAll(int current_page);
	
	public ReportBFVo selectByNo(int no);
	
	public int getReportPageCount();
	
}
