package com.give.donagi.volunteer.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.give.donagi.vo.CommentRVVo;
import com.give.donagi.vo.JoinVVo;
import com.give.donagi.vo.MSTVCategoryVo;
import com.give.donagi.vo.MSTVDateVo;
import com.give.donagi.vo.MSTVImgVo;
import com.give.donagi.vo.MSTVStatusVo;
import com.give.donagi.vo.MSTVolunteerVo;
import com.give.donagi.vo.RecommendRVVo;
import com.give.donagi.vo.ReviewVolunteerVo;

public interface UserVolunteerSQLMapper 
{
	public int createMSTVNo(); //volunteer no
	
	public int createMSTVDNo(); //volunteer_date no
	
	public int createVImgNo(); // volunteer_img no
	
	public void insertVImage(MSTVImgVo mstVimgvo); //이미지 링크 insert
	
	public void insertMSTVolunteer(MSTVolunteerVo mstVolunteerVo);
	
	public void insertMSTDate(MSTVDateVo mstVDateVo);
	
	public int pageCount(HashMap<String, Object> searchMap);
	
	public int page();
	
	public int JoinCount(int mst_v_no);
	
	public ArrayList<MSTVCategoryVo> selectMSTVCategory();
	
	public ArrayList<MSTVolunteerVo> selectVolunteerList(HashMap<String, Object> searchMap);
	
	public MSTVolunteerVo selectVolunteerByNo(int mst_v_no); // 펀딩 정보 받기
	
	public ArrayList<MSTVDateVo> selectDateByVolunteerNo(int mst_v_no); // 펀딩하나의 옵션 받기
	
	public MSTVDateVo selectDateByDateNo(int mst_vd_no); // 날짜 번호로 날짜 찾기
	
	public int createJVNo(); //참여정보 no 
	
	public void insetJV(JoinVVo joinVo); // 침여정보 insert
	
	public ArrayList<JoinVVo> selectJVByMNo(int m_no); //회원에 따른 참여정보 select 
	
	public ArrayList<JoinVVo> selectJVByVNo(int mst_v_no); //해당 봉사 참여 정보 받기
	
	public MSTVDateVo selectdateByVDNo(int mst_vd_no); //해당 날짜선택
	
	public MSTVStatusVo selectJoinStatus(int mst_vs_no); // 해당 참여상태 select 
	
	public MSTVImgVo selectVImgByVNo(int mst_v_no); //이미지 select
	
	public MSTVCategoryVo selectVCategoryByVCNo(int mst_vc_no);
	
	public ArrayList<MSTVolunteerVo> selectVolunteerListByMNo(int m_no); 
	
	public void deleteByVNo(int mst_v_no);
	
	public void deleteJoinV(int jv_no);
	
	public void deleteJoinVByVNo(int mst_v_no);
		
	public void updateVStatusP(int jv_no); //참가 승인

	public void updateVStatusF(int jv_no); //참가 거절
	
	public int createRVKey(); // review volunteer no
	
	public void insertRV(ReviewVolunteerVo rvvo); // 후기 작성
	
	public ArrayList<ReviewVolunteerVo> selectReviewVolList(int page_no); // 후기 리스트
	
	public ReviewVolunteerVo selectRV(int rv_no); // 후기 상세
	
	public JoinVVo selectByNo(int jv_no);
	
	public void deleteRV(int rv_no); // 후기 삭제
	
	public void updateRV(ReviewVolunteerVo vo); // 후기 수정
	
	public int getRVPageCount();
	
	public ArrayList<ReviewVolunteerVo> selectReviewByNo(int mst_v_no); // 탭 후기 가져오기
	
	
	// 후기 검색
	
	public ArrayList<ReviewVolunteerVo> searchTitle(String search_word);
	
	public ArrayList<ReviewVolunteerVo> searchContent(String search_word);
	
	public ArrayList<ReviewVolunteerVo> searchWriter(String search_word);
	
	
	// 후기 추천
	
	public int createRno();
	
	public void doRecommend(RecommendRVVo recommendRVVo); // 추천
	
	public void cancelRecommend(RecommendRVVo recommendRVVo); // 추천 취소
	
	public RecommendRVVo checkRecommend(RecommendRVVo recommendRVVo); // 추천 체크
	
	public int countRecommend(int rv_no); // 추천수 카운트
	
	
	// 후기 댓글
	
	public int createRCno();
	
	public void insertRVComment(CommentRVVo vo); // 후기 댓글 작성
	
	public ArrayList<CommentRVVo> selectRVComment(int rv_no); // 후기 댓글 select
	
	public void updateRVComment(CommentRVVo vo); // 후기 댓글 수정
	
	public void deleteRVComment(int crv_no); // 후기 댓글 삭제
	
	public int selectRVCommentCount(int rv_no); // 후기 댓글 수 카운트
	
	//중복검사
	
	public int selectReviewCount(int jv_no);
}
