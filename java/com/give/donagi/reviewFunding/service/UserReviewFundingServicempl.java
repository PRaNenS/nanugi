package com.give.donagi.reviewFunding.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.sql.CommonDataSource;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.give.donagi.manager.mapper.ManagerSQLMapper;
import com.give.donagi.member.mapper.UserMemberSQLMapper;
import com.give.donagi.reviewFunding.mapper.UserCommentSQLMapper;
import com.give.donagi.reviewFunding.mapper.UserRFRecommendSQLMapper;
import com.give.donagi.reviewFunding.mapper.UserReportSQLMapper;
import com.give.donagi.reviewFunding.mapper.UserReviewFundingSQLMapper;
import com.give.donagi.util.CommonsDateConverter;
import com.give.donagi.vo.CommentRFVo;
import com.give.donagi.vo.MSTFundingVo;
import com.give.donagi.vo.MemberVo;
import com.give.donagi.vo.OrderFVo;
import com.give.donagi.vo.RecommendRFVo;
import com.give.donagi.vo.ReportRFVo;
import com.give.donagi.vo.ReviewFundingVo;


@Service
public class UserReviewFundingServicempl {
	
	@Autowired
	private UserReportSQLMapper userReportSQLMapper;
	
	@Autowired
	private UserMemberSQLMapper userMemberSQLMapper;
	
	@Autowired
	private  UserReviewFundingSQLMapper userReviewFundingSQLMapper; 
	
	@Autowired
	private UserRFRecommendSQLMapper userRFRecommendSQLMapper;
	
	@Autowired
	private UserCommentSQLMapper userCommentSQLMapper;
	
	@Autowired
	private ManagerSQLMapper managerSQLMapper;
	
	public void createReviewFunding(ReviewFundingVo reviewFundingVo) {
		
		int PKNO = userReviewFundingSQLMapper.createRFNo();
		reviewFundingVo.setRf_no(PKNO);
		userReviewFundingSQLMapper.insert(reviewFundingVo);
	}
	
	public ArrayList<HashMap<String, Object>> getReportList(){
		
		ArrayList<HashMap<String, Object>> resultList =
				new ArrayList<HashMap<String, Object>>();
		
		ReviewFundingVo reviewFundingVo = new ReviewFundingVo();
		
		ArrayList<ReportRFVo> RRFList = null;
		
		RRFList = userReviewFundingSQLMapper.reportByNo();
		
		for(ReportRFVo reportRFVo : RRFList) {
			
			
			MemberVo memberVo = userMemberSQLMapper.selectByNo(reportRFVo.getM_no());
			int confirm = managerSQLMapper.isPenalty(reportRFVo.getM_no());
			
			reviewFundingVo = userReviewFundingSQLMapper.selectByNo(reportRFVo.getRf_no());
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("memberVo", memberVo);
			map.put("reviewFundingVo", reviewFundingVo);
			map.put("reportRFVo", reportRFVo);
			map.put("confirm", confirm);
			
			resultList.add(map);
		}
		
		return resultList;
		
	}
	
	
	public ArrayList<HashMap<String, Object>> getReviewFundingList(int page_num,String search_word , String search_type){
		ArrayList<HashMap<String, Object>> resultList =
				new ArrayList<HashMap<String, Object>>();
		
		ArrayList<ReviewFundingVo> RFList = null;
		
		if(search_word == null || search_type == null) {
			RFList = userReviewFundingSQLMapper.selectAll(page_num);
		}else {
			if(search_type.equals("title")) {
				RFList = userReviewFundingSQLMapper.selectByTitle(search_word);
			}else if(search_type.equals("content")) {
				RFList = userReviewFundingSQLMapper.selectByContent(search_word);
			}else if(search_type.equals("writer")) {
				RFList = userReviewFundingSQLMapper.selectByWriter(search_word);
			}else {
				RFList = userReviewFundingSQLMapper.selectAll(page_num);
			}
		}
		
		
		
		for(ReviewFundingVo reviewFundingVo : RFList) {
			CommonsDateConverter dateConverter = new CommonsDateConverter();
			MemberVo memberVo = userMemberSQLMapper.selectByNo(reviewFundingVo.getM_no());
			
			int of_no = reviewFundingVo.getOf_no();
			OrderFVo orderFVo = userReviewFundingSQLMapper.selectofByOFNo(of_no);
			
			int commentCount = userCommentSQLMapper.reviewCount(reviewFundingVo.getRf_no());
			
			int ReCommendVo = userRFRecommendSQLMapper.likeCount(reviewFundingVo.getRf_no());
			//int mst_f_no = orderFVo.getMst_f_no();
			//userReviewFundingSQLMapper.selectreviewByno(mst_f_no);
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("memberVo", memberVo);
			map.put("reviewFundingVo", reviewFundingVo);
			map.put("date", dateConverter.getDate(reviewFundingVo.getRf_write_date()));
			map.put("orderFVo",orderFVo);
			map.put("ReCommendVo",ReCommendVo);
			map.put("orderFVo",orderFVo);
			map.put("commentCount", commentCount);
			
			resultList.add(map);
		}
		return resultList;
	}
	
	public ArrayList<HashMap<String, Object>> getFundingReadList(int mst_f_no){
		
		ArrayList<HashMap<String, Object>> frfList =
				new ArrayList<HashMap<String, Object>>();
		
		ArrayList<ReviewFundingVo> RFFList = null;
		
		RFFList = userReviewFundingSQLMapper.selectreviewByno(mst_f_no);
		
		for(ReviewFundingVo reviewFundingVo : RFFList) {
			
			CommonsDateConverter dateConverter = new CommonsDateConverter();
			MemberVo memberVo = userMemberSQLMapper.selectByNo(reviewFundingVo.getM_no());
			
			int of_no = reviewFundingVo.getOf_no();
			OrderFVo orderFVo = userReviewFundingSQLMapper.selectofByOFNo(of_no);
		
			int ReCommendVo = userRFRecommendSQLMapper.likeCount(reviewFundingVo.getRf_no());
			
			int commentCount = userCommentSQLMapper.reviewCount(reviewFundingVo.getRf_no());
			
			HashMap<String, Object> mstmap = new HashMap<String, Object>();
			
			mstmap.put("memberVo", memberVo);
			mstmap.put("reviewFundingVo", reviewFundingVo);
			mstmap.put("date", dateConverter.getDate(reviewFundingVo.getRf_write_date()));
			mstmap.put("orderFVo",orderFVo);
			mstmap.put("ReCommendVo",ReCommendVo);
			mstmap.put("commentCount", commentCount);
			
			frfList.add(mstmap);
		}
		
		return frfList;
	}
	
	public HashMap<String, Object> getReviewRead(int rf_no, HashMap<String, Object> loginUser){
		
		
		MemberVo loginData = (MemberVo) loginUser.get("memberVo");
		ReviewFundingVo reviewFundingVo = userReviewFundingSQLMapper.selectByNo(rf_no);
		
		
		int m_no = reviewFundingVo.getM_no();
		MemberVo memberVo = userMemberSQLMapper.selectByNo(m_no);
		
		CommonsDateConverter dateConverter = new CommonsDateConverter();
		
		RecommendRFVo recommendRFVo = new RecommendRFVo();
		recommendRFVo.setRf_no(rf_no);
		recommendRFVo.setM_no(loginData.getM_no());
		
		RecommendRFVo resultReCommendVo = userRFRecommendSQLMapper.selectByNo(recommendRFVo);		
		
		int RecommendCount = userRFRecommendSQLMapper.likeCount(reviewFundingVo.getRf_no());
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("memberVo", memberVo);
		map.put("reviewFundingVo", reviewFundingVo);
		map.put("date", dateConverter.getDate(reviewFundingVo.getRf_write_date()));
		map.put("resultReCommendVo",resultReCommendVo);
		map.put("RecommendCount",RecommendCount);
		
		return map;
	}
		
	public void ReviewUpdate(ReviewFundingVo reviewFundingVo) {
		
		userReviewFundingSQLMapper.update(reviewFundingVo);
	}
	
	public void ReviewDelete(int no) {
		
		userReviewFundingSQLMapper.deleteByNo(no);
	}
		
	
	public void CommentWrite(CommentRFVo commentRFVo) {
		int PK = userCommentSQLMapper.createCRFNo();
		commentRFVo.setCrf_no(PK);
		
		userCommentSQLMapper.insert(commentRFVo);
		
	}
	
	public ArrayList<HashMap<String, Object>> getCommentList(int rf_no) {
		
		ReviewFundingVo reviewFundingVo = new ReviewFundingVo();
		
		ArrayList<HashMap<String, Object>> result = 
				new ArrayList<HashMap<String, Object>>();
		
		ArrayList<CommentRFVo> commentRFVoList = userCommentSQLMapper.selectByCommentNo(rf_no);
	
		for(CommentRFVo commentRFVo : commentRFVoList) {
			
			int m_no = commentRFVo.getM_no();
			commentRFVo.setRf_no(reviewFundingVo.getRf_no());
			
			MemberVo memberVo = userMemberSQLMapper.selectByNo(m_no);
			
			CommonsDateConverter dateConverter = new CommonsDateConverter();
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("memberVo",memberVo);
			map.put("commentRFVo",commentRFVo);
			map.put("date", dateConverter.getDate(commentRFVo.getCrf_write_date()));
			
			result.add(map);
						
		}
		
		return result;
	}
	
	public void CommentDelete(int crf_no) {
		
		userCommentSQLMapper.reviewDelete(crf_no);
	}
		
		 
		
	
	public void ReCommend(int rf_no, int m_no) {
		
		int RECpk = userRFRecommendSQLMapper.createRRFNo();
		
		RecommendRFVo recommendRFVo = new RecommendRFVo();
		
		recommendRFVo.setRf_no(rf_no);
		recommendRFVo.setRrf_no(RECpk);
		recommendRFVo.setM_no(m_no);
		
		userRFRecommendSQLMapper.insert(recommendRFVo);
		
	}
	
	public void ReCommendDel(int rf_no, int m_no) {
		
		RecommendRFVo recommendRFVo = new RecommendRFVo();
		
		recommendRFVo.setRf_no(rf_no);
		recommendRFVo.setM_no(m_no);
		
		userRFRecommendSQLMapper.deleteByNo(recommendRFVo);
		
	}
	
	public int getPageCount() {
		
		return userReviewFundingSQLMapper.getPageCount();
	}
	
	public void report(ReportRFVo reportRFVo) {
		
		int PKNo = userReportSQLMapper.createRerfNo();
		
		reportRFVo.setRerf_no(PKNo);
		
		userReportSQLMapper.insert(reportRFVo);
		
	}
	
	
	
	
	
	
}
