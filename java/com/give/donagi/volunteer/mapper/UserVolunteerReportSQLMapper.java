package com.give.donagi.volunteer.mapper;

import java.util.ArrayList;

import com.give.donagi.vo.ReportRVVo;
import com.give.donagi.vo.ReviewVolunteerVo;

public interface UserVolunteerReportSQLMapper {

	// 후기 신고
	
		public void insertReport(ReportRVVo vo);
		
		public ArrayList<ReportRVVo> selectReportAll(int current_page);
		
		public ReviewVolunteerVo selectByRvNo(int rv_no);
		
		public int getReportPageCount();
		
	
}
