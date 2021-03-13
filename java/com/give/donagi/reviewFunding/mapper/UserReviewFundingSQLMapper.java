package com.give.donagi.reviewFunding.mapper;

import java.util.ArrayList;

import com.give.donagi.vo.OrderFVo;
import com.give.donagi.vo.ReportRFVo;
import com.give.donagi.vo.ReviewFundingVo;

public interface UserReviewFundingSQLMapper {
	
	public int createRFNo();//키생성
	
	public void insert(ReviewFundingVo reviewFundingVo);//글쓰기
	
	public ArrayList<ReviewFundingVo> selectAll(int page_num); //전체글출력
	
	public ReviewFundingVo selectByNo(int no);//글읽기
	
	public void deleteByNo(int no);//글 삭제
	
	public int getPageCount();//페이지 개수
	
	public void update(ReviewFundingVo reviewFundingVo);
	
	public int countOFNo(int of_no);
	
	public OrderFVo selectofByOFNo(int of_no);
	
	public ArrayList<ReviewFundingVo> selectreviewByno(int mst_f_no);
	
	public ArrayList<ReportRFVo> reportByNo();
	
	
	public ArrayList<ReviewFundingVo> selectByTitle(String search_word);//제목 검색
	
	public ArrayList<ReviewFundingVo> selectByContent(String search_word);//내용 검색
			
	public ArrayList<ReviewFundingVo> selectByWriter(String search_word);//작성자 검색
	
	
	
}
