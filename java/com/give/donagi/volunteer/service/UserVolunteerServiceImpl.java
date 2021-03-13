package com.give.donagi.volunteer.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.give.donagi.manager.mapper.ManagerSQLMapper;
import com.give.donagi.member.mapper.UserMemberSQLMapper;
import com.give.donagi.util.CommonsDateConverter;
import com.give.donagi.vo.CommentRVVo;
import com.give.donagi.vo.JoinVVo;
import com.give.donagi.vo.MSTVCategoryVo;
import com.give.donagi.vo.MSTVDateVo;
import com.give.donagi.vo.MSTVImgVo;
import com.give.donagi.vo.MSTVStatusVo;
import com.give.donagi.vo.MSTVolunteerVo;
import com.give.donagi.vo.MemberVo;
import com.give.donagi.vo.RecommendRVVo;
import com.give.donagi.vo.ReportRVVo;
import com.give.donagi.vo.ReviewVolunteerVo;
import com.give.donagi.volunteer.mapper.UserVolunteerReportSQLMapper;
import com.give.donagi.volunteer.mapper.UserVolunteerSQLMapper;

@Service
public class UserVolunteerServiceImpl 
{
	@Autowired
	private UserVolunteerSQLMapper volunteerMapper;
	
	@Autowired
	private UserMemberSQLMapper memberMapper;
	
	@Autowired
	private UserVolunteerReportSQLMapper volunteerReportMapper;
	
	@Autowired
	private ManagerSQLMapper managerSQLMapper;
	
	public ArrayList<MSTVCategoryVo> getCategoryList()
	{
		System.out.println("[System] getCategory");
		
		return this.volunteerMapper.selectMSTVCategory();
	}
	
	public int getPageCount(HashMap<String, Object> searchMap)
	{
		int tempPage = this.volunteerMapper.pageCount(searchMap);

		int pageCount = 0; // 페이지 수
		
		if(tempPage > 0) { // 검색 조건에 맞는 결과가 있을 경우
			pageCount = (int)tempPage;
			
		}else { // 검색 조건에 맞는 결과가 없을 경우
			pageCount = 1;
		}
		
		return pageCount;
	}
	
	
	public int createVolunteer(HashMap<String, Object> loginUser, MSTVolunteerVo mstVolunteerVo)
	{
		int mstVNo = this.volunteerMapper.createMSTVNo();
		
		System.out.println(mstVNo);
		
		mstVolunteerVo.setMst_v_no(mstVNo); //volunteer table
		
		mstVolunteerVo.setM_no(((MemberVo)loginUser.get("memberVo")).getM_no());
		
		this.volunteerMapper.insertMSTVolunteer(mstVolunteerVo);
				
		
		System.out.println("[System] createvolunteer");
		
		System.out.println("[send] mstVNo");
		
		return mstVolunteerVo.getMst_v_no();
	}
	
	public int PageCount()
	{
		return this.volunteerMapper.page();
	}
	
	public void createVolunteerDate(int mstVNo, String[] mstVdates, String[] mstVdtimes)
	{
		for(int i=0; i<mstVdates.length;i++)
		{
			MSTVDateVo mstVDateVo = new MSTVDateVo(this.volunteerMapper.createMSTVDNo(),mstVNo,mstVdates[i],mstVdtimes[i]);
			
			this.volunteerMapper.insertMSTDate(mstVDateVo);
		}
		
		System.out.println("[System] createVolunteerDate");
	}
	
	public void createVolunteerImg(int mstVNo, String link)
	{

		MSTVImgVo imgvo = new MSTVImgVo(this.volunteerMapper.createVImgNo(),mstVNo,link); //image table
				
		this.volunteerMapper.insertVImage(imgvo);
		
		System.out.println("[System] createVolunteerImg");
	}
	
	public ArrayList<HashMap<String, Object>> getVolunteerList(HashMap<String, Object> searchMap)
	{
		ArrayList<HashMap<String, Object>> VolunteerList = new ArrayList<HashMap<String,Object>>();
		ArrayList<MSTVolunteerVo> tempVolunteerList = this.volunteerMapper.selectVolunteerList(searchMap);
		CommonsDateConverter dateConvert = new CommonsDateConverter();
		
		if(tempVolunteerList != null)
		{
			for(MSTVolunteerVo mstVolunteerVo : rebuildVolunteerList(tempVolunteerList,Integer.parseInt((searchMap.get("status")).toString())))
			{
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				map.put("vcategoryvo", this.volunteerMapper.selectVCategoryByVCNo(mstVolunteerVo.getMst_vc_no()));
				map.put("imgvo", this.volunteerMapper.selectVImgByVNo(mstVolunteerVo.getMst_v_no()));
				map.put("memberVo", this.memberMapper.selectByNo(mstVolunteerVo.getM_no()));
				map.put("mstVolunteerVo", mstVolunteerVo);
				map.put("dateFrom", dateConvert.getDate(mstVolunteerVo.getmst_v_from()));
				map.put("dateTo", dateConvert.getDate(mstVolunteerVo.getmst_v_to()));
				map.put("volunteerStauts", dateConvert.getDateStatus(mstVolunteerVo.getmst_v_from(), mstVolunteerVo.getmst_v_to()));
				
				VolunteerList.add(map);
			}
		}
		else
		{
			VolunteerList=null;
		}
		
		System.out.println("[System] getVolunteerList");
		
		return VolunteerList;
	}
	private ArrayList<MSTVolunteerVo> rebuildVolunteerList(ArrayList<MSTVolunteerVo> list, int targetStatus) {
		ArrayList<MSTVolunteerVo> resultList = new ArrayList<MSTVolunteerVo>();
		CommonsDateConverter dateConverter = new CommonsDateConverter();
		
		if(targetStatus == 0) { // 검색조건이 없을 시
			resultList = list;
			
		}else { // 검색조건이 있을 시
		
			for(MSTVolunteerVo vo: list) {
				int status = dateConverter.getDateStatus(vo.getmst_v_from(), vo.getmst_v_to());
				
				if(status == targetStatus) {
					resultList.add(vo);
				}
			}
		}
		
		return resultList;
	}
	
	public HashMap<String, Object> getVolunteer(int mst_v_no)
	{
		CommonsDateConverter dateConvert = new CommonsDateConverter();
		HashMap<String, Object> VolunteerData = new HashMap<String, Object>();
		
		MSTVolunteerVo mstVolunteerVo = this.volunteerMapper.selectVolunteerByNo(mst_v_no);
		int joinCount = this.volunteerMapper.JoinCount(mst_v_no);
		double goal = mstVolunteerVo.getMst_v_goal();	
		double progress = (joinCount/goal)*100;
	
		System.out.println(joinCount);
		System.out.println(goal);
		System.out.println(progress);
		
		String date_from = dateConvert.getDate(mstVolunteerVo.getmst_v_from());
		String date_to = dateConvert.getDate(mstVolunteerVo.getmst_v_to());
		
		String str = mstVolunteerVo.getMst_v_content();
		str = StringEscapeUtils.escapeHtml4(str);
		str = str.replaceAll("\n", "<br>");
		mstVolunteerVo.setMst_v_content(str);
		
		VolunteerData.put("memberVo", this.memberMapper.selectByNo(mstVolunteerVo.getM_no()));
		VolunteerData.put("imgvo", this.volunteerMapper.selectVImgByVNo(mstVolunteerVo.getMst_v_no()));
		VolunteerData.put("vcategoryvo", this.volunteerMapper.selectVCategoryByVCNo(mstVolunteerVo.getMst_vc_no()));
		VolunteerData.put("date_from", date_from);
		VolunteerData.put("date_to", date_to);
		VolunteerData.put("mstVolunteerVo", mstVolunteerVo);
		VolunteerData.put("joinCount",joinCount);
		VolunteerData.put("progress", progress);
		VolunteerData.put("dDay", dateConvert.getDday(mstVolunteerVo.getmst_v_to()));
		VolunteerData.put("volunteerStauts", dateConvert.getDateStatus(mstVolunteerVo.getmst_v_from(), mstVolunteerVo.getmst_v_to()));
		
		System.out.println("[System] getVolunteer");
		
		return VolunteerData;
	}
	
	public ArrayList<MSTVDateVo> getDateList(int mst_v_no)
	{
		return this.volunteerMapper.selectDateByVolunteerNo(mst_v_no);
	}
	
	public ArrayList<HashMap<String, Object>> setVDate(int[] mstVdNo)
	{
		ArrayList<HashMap<String, Object>> joinDateList = new ArrayList<HashMap<String,Object>>();

		
		for(int i=0; i<mstVdNo.length; i++)
		{
			HashMap<String, Object> Date = new HashMap<String, Object>();
			
			MSTVDateVo mstVDateVo = this.volunteerMapper.selectDateByDateNo(mstVdNo[i]);
			Date.put("mstVDateVo", mstVDateVo);
			
			joinDateList.add(Date);
			
		}
		System.out.println("[Send] selectDate");
		return joinDateList;
	}
	
	public void insertJv(HashMap<String, Object> loginUser,JoinVVo joinParam,int[] VDNo,int mst_v_no)
	{
		for(int i =0; i<VDNo.length;i++)
		{
			joinParam.setjv_no(this.volunteerMapper.createJVNo());
			joinParam.setM_no(((MemberVo)loginUser.get("memberVo")).getM_no());
			joinParam.setMst_v_no(mst_v_no);
			joinParam.setMst_vd_no(VDNo[i]);
			joinParam.setMst_vs_no(1);
			
			this.volunteerMapper.insetJV(joinParam);		
		}
		System.out.println("[System] insertJV");
	}
	
	public ArrayList<HashMap<String, Object>> getJVList(MemberVo membervo)
	{
		ArrayList<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		ArrayList<JoinVVo> JVlist = this.volunteerMapper.selectJVByMNo(membervo.getM_no());
		
		for(JoinVVo jvo : JVlist)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			HashMap<String, Object> volunteer = getVolunteer(jvo.getMst_v_no());
			MSTVDateVo date = this.volunteerMapper.selectdateByVDNo(jvo.getMst_vd_no());
			MSTVStatusVo status = this.volunteerMapper.selectJoinStatus(jvo.getMst_vs_no());
			
			int count = this.volunteerMapper.selectReviewCount(jvo.getjv_no());
			
			CommonsDateConverter dateConvert = new CommonsDateConverter();
			
			map.put("jvo", jvo);
			map.put("volunteer", volunteer);
			map.put("date", date);
			map.put("status", status);
			map.put("joindate",dateConvert.getDate(jvo.getJv_submit_date()));
			map.put("count", count);
			
			resultList.add(map);
			
		}
		
		return resultList;
	}
	
	public ArrayList<HashMap<String, Object>> OpenedVolunteer(MemberVo memberVo)
	{
		ArrayList<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		ArrayList<MSTVolunteerVo> volunteerList = this.volunteerMapper.selectVolunteerListByMNo(memberVo.getM_no());
		
		for(MSTVolunteerVo volunteer : volunteerList)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			CommonsDateConverter dateConverter = new CommonsDateConverter();
			
			int joinCount = this.volunteerMapper.JoinCount(volunteer.getMst_v_no());
			double goal = volunteer.getMst_v_goal();	
			double progress = (joinCount/goal)*100;
			
			
			map.put("joinCount",  this.volunteerMapper.JoinCount(volunteer.getMst_v_no()));
			map.put("imgvo", this.volunteerMapper.selectVImgByVNo(volunteer.getMst_v_no()));
			map.put("volunteerVo", volunteer);
			map.put("volunteerStatus", dateConverter.getDateStatus(volunteer.getmst_v_from(), volunteer.getmst_v_to()));
			map.put("createDate", dateConverter.getDate(volunteer.getMst_v_create_date()));
			map.put("dateFrom", dateConverter.getDate(volunteer.getmst_v_from()));
			map.put("dateTo", dateConverter.getDate(volunteer.getmst_v_to()));
			map.put("progress", progress);
			
			resultList.add(map);
		}
		return resultList;
	}
	
	public void deleteVolunteer(int mst_v_no)
	{
		volunteerMapper.deleteByVNo(mst_v_no);
		volunteerMapper.deleteJoinVByVNo(mst_v_no);
	}
	
	public void deleteJoinv(int jv_no)
	{
		volunteerMapper.deleteJoinV(jv_no);
	}
	
	
	public void updateStatusP(int jv_no) //참가 승인
	{
		volunteerMapper.updateVStatusP(jv_no);
	}
	
	public void updateStatusF(int jv_no) // 참가 거절
	{
		volunteerMapper.updateVStatusF(jv_no);
	}
	
	public ArrayList<HashMap<String, Object>> detailList(int mst_v_no)
	{
		ArrayList<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		ArrayList<JoinVVo> detailList = this.volunteerMapper.selectJVByVNo(mst_v_no);
		
		for(JoinVVo detail : detailList)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			CommonsDateConverter dateConverter = new CommonsDateConverter();
			MSTVolunteerVo volunteer = this.volunteerMapper.selectVolunteerByNo(mst_v_no);
			
			int joinCount = this.volunteerMapper.JoinCount(volunteer.getMst_v_no());
			double goal = volunteer.getMst_v_goal();	
			double progress = (joinCount/goal)*100;
			
			map.put("imgvo", this.volunteerMapper.selectVImgByVNo(volunteer.getMst_v_no()));
			map.put("detail", detail);
			map.put("membervo", this.memberMapper.selectByNo(detail.getM_no()));
			map.put("datevo", this.volunteerMapper.selectdateByVDNo(detail.getMst_vd_no()));
			map.put("volunteer",this.volunteerMapper.selectVolunteerByNo(mst_v_no));
			map.put("dateFrom", dateConverter.getDate(volunteer.getmst_v_from()));
			map.put("dateTo", dateConverter.getDate(volunteer.getmst_v_to()));
			map.put("progress", progress);
			
			resultList.add(map);
		}
		return resultList;
	}
	
	// ====== 후기 관련 =======
	
		public void writeRV(ReviewVolunteerVo vo){
			
			int pk = volunteerMapper.createRVKey();
			vo.setRv_no(pk);
			volunteerMapper.insertRV(vo);
		}
		

		// 봉사후기 페이지 리스트
		public ArrayList<HashMap<String, Object>> getReviewVolList(String search_word, String search_type, int page_no){
			ArrayList<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
			
			ArrayList<ReviewVolunteerVo> reviewList = null;
			
			// 검색
			if(search_word == null || search_type == null) {
				reviewList = volunteerMapper.selectReviewVolList(page_no);
			}else {
				if(search_type.equals("title")) {
					reviewList = volunteerMapper.searchTitle(search_word);
				}else if(search_type.equals("content")){
					reviewList = volunteerMapper.searchContent(search_word);
				}else if(search_type.equals("writer")) {
					reviewList = volunteerMapper.searchWriter(search_word);
				}else {
					reviewList = volunteerMapper.selectReviewVolList(page_no);
				}
			}
			
			for(ReviewVolunteerVo reviewVolunteerVo : reviewList) {
				int m_no = reviewVolunteerVo.getM_no();
				MemberVo memberVo = memberMapper.selectByNo(m_no);
				
				int commentCount = volunteerMapper.selectRVCommentCount(reviewVolunteerVo.getRv_no());
				
				int recommendCount = volunteerMapper.countRecommend(reviewVolunteerVo.getRv_no());
				
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("memberVo", memberVo);
				map.put("reviewVolunteerVo", reviewVolunteerVo);
				map.put("commentCount", commentCount);
				map.put("recommendCount", recommendCount);
				
				resultList.add(map);
				
			}
			
			return resultList;
			
		}
		
		// 탭
		public ArrayList<HashMap<String, Object>> getTapReviewList(int mst_v_no){
			ArrayList<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
			
			ArrayList<ReviewVolunteerVo> reviewResultList = null;
			
			reviewResultList = volunteerMapper.selectReviewByNo(mst_v_no);
			
			for(ReviewVolunteerVo reviewVolunteerVo : reviewResultList) {
				MemberVo memberVo = memberMapper.selectByNo(reviewVolunteerVo.getM_no());
				
				
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("memberVo", memberVo);
				map.put("reviewVolunteerVo", reviewVolunteerVo);
				
				
				System.out.println(reviewVolunteerVo.getRv_title());
				
				resultList.add(map);
				
				
			}
			
			return resultList;
		}
		
		
		// 봉사 후기 상세페이지
		public HashMap<String, Object> getReviewVol(int rv_no, HashMap<String, Object>loginUser){
			
			// 글 출력데이터
			ReviewVolunteerVo rvvo = volunteerMapper.selectRV(rv_no);
			
			rvvo.getM_no();
			int m_no = rvvo.getM_no();
			MemberVo memberVo = memberMapper.selectByNo(m_no);
			
			// 추천 가져오기
			RecommendRVVo recommendRVVo = new RecommendRVVo();
			recommendRVVo.setM_no(((MemberVo)loginUser.get("memberVo")).getM_no());
			recommendRVVo.setRv_no(rv_no);
			RecommendRVVo recommendVo = volunteerMapper.checkRecommend(recommendRVVo);
			
			// 비추천
			RecommendRVVo cancelRecommendRVVo = new RecommendRVVo();
			cancelRecommendRVVo.setM_no(m_no);
			cancelRecommendRVVo.setRv_no(rv_no);
			RecommendRVVo cancelRecommendVo = volunteerMapper.checkRecommend(cancelRecommendRVVo);
			
			// 추천수
			RecommendRVVo countRecommendRVVo = new RecommendRVVo();
			ReviewVolunteerVo reviewBoardVo = new ReviewVolunteerVo();
			reviewBoardVo.getRv_no();
			countRecommendRVVo.setRv_no(rv_no);
			
			int countRVo = volunteerMapper.countRecommend(rv_no);
			
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("reviewVolunteerVo", rvvo);
			map.put("memberVo", memberVo);
			map.put("recommendVo", recommendVo);
			map.put("cancelRecommendVo", cancelRecommendVo);
			map.put("countRVo", countRVo);
			
			
			ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
			
			ArrayList<CommentRVVo> commentList = volunteerMapper.selectRVComment(rv_no);
			
			for(CommentRVVo commentRVVo : commentList) {
				MemberVo commentMemberVo = memberMapper.selectByNo(commentRVVo.getM_no());
				
				HashMap<String, Object> commentMap = new HashMap<String, Object>();
				commentMap.put("commentMemberVo", commentMemberVo);
				commentMap.put("commentRVVo", commentRVVo);
				
				result.add(commentMap);
			}
			
			map.put("commentList", result);
			
			return map;
		}

		
		
			// 삭제
			public void deleteReviewVol(int rv_no) {
				volunteerMapper.deleteRV(rv_no);
			}
			
			// 수정
			public void updateReviewVol(ReviewVolunteerVo vo) {
				volunteerMapper.updateRV(vo);
			}
			
			// 페이징
			public int getReviewVolPageCount() {
				return volunteerMapper.getRVPageCount();
			}
			
			// 코멘트 작성
			public void writeReviewVolComment(CommentRVVo vo) {
				volunteerMapper.insertRVComment(vo);
			}
			
			// 코멘트
			public ArrayList<HashMap<String, Object>> getReviewVolComment(int rv_no){
				ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
				
				ArrayList<CommentRVVo> commentList = volunteerMapper.selectRVComment(rv_no);
				
				for(CommentRVVo commentRVVo : commentList) {
					MemberVo memberVo = memberMapper.selectByNo(commentRVVo.getM_no());
					
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("memberVo", memberVo);
					map.put("commentRVVo", commentRVVo);
					
					result.add(map);
				}
				
				return result;
			}
			
			// 코멘트 삭제
			public void deleteReviewVolComment(int crv_no) {
				volunteerMapper.deleteRVComment(crv_no);
			}
			
			// 추천
			public void recommend(RecommendRVVo vo) {
				int rrv_no = volunteerMapper.createRno();
				
				vo.setRrv_no(rrv_no);
				volunteerMapper.doRecommend(vo);
			}
			
			// 추천 취소
			public void cancelRecommend(RecommendRVVo vo) {
				volunteerMapper.cancelRecommend(vo);
			}
			
			// 추천 중복체크
			public void ckRecommend(RecommendRVVo vo) {
				volunteerMapper.checkRecommend(vo);
			}
			
			
			/* 신고 */
			
			public void writeReport(ReportRVVo vo) {
				volunteerReportMapper.insertReport(vo);
			}
			
			// 관리자 > 봉사후기 신고 main
			public ArrayList<HashMap<String, Object>> getReportList(int current_page){
				ArrayList<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
				
				ArrayList<ReportRVVo> reportList = null;
				
				reportList = volunteerReportMapper.selectReportAll(current_page);
				
				for(ReportRVVo reportRvVo : reportList) {
					int m_no = reportRvVo.getM_no();
					MemberVo memberVo = memberMapper.selectByNo(m_no);
					int confirm = managerSQLMapper.isPenalty(m_no);
					int rv_no = reportRvVo.getRv_no();
					ReviewVolunteerVo rvvo = volunteerReportMapper.selectByRvNo(rv_no);
					
					HashMap<String, Object> map = new HashMap<String, Object>();
					
					// 전송
					map.put("reportRvVo", reportRvVo);
					map.put("memberVo", memberVo);
					map.put("rvvo", rvvo);
					map.put("confirm", confirm);
					
					resultList.add(map);
				
				}
				
				return resultList;
			}
			
			public int getVolReportPageCount() {
				return volunteerReportMapper.getReportPageCount();
				
			}
}
