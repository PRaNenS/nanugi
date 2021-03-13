package com.give.donagi.notice.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.give.donagi.member.mapper.UserMemberSQLMapper;
import com.give.donagi.notice.mapper.RecommendSQLMapper;
import com.give.donagi.notice.mapper.UserNoticeSQLMapper;
import com.give.donagi.util.CommonsDateConverter;
import com.give.donagi.vo.BNoticeVo;
import com.give.donagi.vo.MemberVo;
import com.give.donagi.vo.NoticeRecommendVo;

@Service
public class UserNoticeServiceImpl 
{
	@Autowired
	private UserNoticeSQLMapper userNoticeSQLMapper;
	
	@Autowired
	private UserMemberSQLMapper userMemberSQLMapper;
	
	@Autowired
	private RecommendSQLMapper recommendSQLMapeer;
	
	public ArrayList<HashMap<String, Object>> getNoticeList(int page_num) 
	{
		ArrayList<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>(); 
		
		ArrayList<BNoticeVo> noticeList = null;
		

		noticeList =  userNoticeSQLMapper.selectAll(page_num); 
		
		
		for(BNoticeVo noticevo :noticeList) 
		{
			int member_no = noticevo.getM_no(); 
			
		 	MemberVo membervo =  userMemberSQLMapper.selectByNo(member_no); 
		 	
		 	HashMap<String, Object> map = new HashMap<String, Object>(); 
		 	
		 	CommonsDateConverter dateConvert = new CommonsDateConverter();
		 	map.put("membervo", membervo); //  
		 	map.put("noticevo", noticevo); // 
		 	map.put("date",dateConvert.getDate(noticevo.getBn_write_date()));
		 	
		 	if(noticevo.getBn_write_date().getTime() > (System.currentTimeMillis() - 1000*60*60*24) ) 
		 	{
		 		map.put("noticeNew", true);
		 	}
		 	else 
		 	{
		 		map.put("noticeNew", false);
		 	}
		 	
		 	resultList.add(map);
		}		
		System.out.println("System log [getNoticeList]");
		return resultList;
	}
	
	public HashMap<String, Object> getContent(int noticeNo, HashMap<String, Object> loginUser) 
	{
		MemberVo loginUserData = (MemberVo) loginUser.get("memberVo");
		
		BNoticeVo noticeVo = userNoticeSQLMapper.selectByNo(noticeNo);
		CommonsDateConverter dateConvert = new CommonsDateConverter();
		NoticeRecommendVo noticeRecommendVo = new NoticeRecommendVo();
		noticeRecommendVo.setBn_no(noticeNo); //추천 해당 글번호 받기
		noticeRecommendVo.setM_no(loginUserData.getM_no()); // 추천 해당 로그인 유저 회원번호 받기
		
		noticeRecommendVo = recommendSQLMapeer.isRecommendCliked(noticeRecommendVo); // 추천이 눌려있는가  체크q
		
		int countRecommend = recommendSQLMapeer.RecommendCount(noticeNo); //추천수 카운트
		
		
		String content = noticeVo.getBn_content();
		content = StringEscapeUtils.escapeHtml4(content);
		content = content.replaceAll("\n", "<br>"); // 공지사항내 줄바꿈 허용
		
		noticeVo.setBn_content(content);
		
		int m_no = noticeVo.getM_no();
		MemberVo memberVo = userMemberSQLMapper.selectByNo(m_no);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memberVo", memberVo);
		map.put("noticeVo", noticeVo);
		map.put("countRecommend", countRecommend);
		map.put("noticeRecommendVo",noticeRecommendVo);
		map.put("date",dateConvert.getDate(noticeVo.getBn_write_date()));
		
		
		System.out.println("System log [getContent]");
		return map;
	}
	
	public void writeContent(BNoticeVo vo)
	{
		int noticepk = userNoticeSQLMapper.createNNo();
		vo.setBn_no(noticepk);
		
		userNoticeSQLMapper.insert(vo);
		System.out.println("[System] writeContent");
	}	
	public int getPageCount()
	{
		System.out.println("[System] getPageCount");
		return userNoticeSQLMapper.getPageCount();
	}
	
	public void deleteContent(int noticeNo)
	{
		userNoticeSQLMapper.deleteByBNo(noticeNo);
		System.out.println("[System] deleteContent");
	}
	
	public void updateContent(BNoticeVo vo)
	{
		userNoticeSQLMapper.update(vo);
		System.out.println("[System] updateContent");
	}

	 public void insertRecommend(NoticeRecommendVo noticeRecommendVo)
	
	{
		 recommendSQLMapeer.insertRecommend(noticeRecommendVo);
		
		System.out.println("[System] insertRecommend");
	}
	
	public void deleteRecommend(NoticeRecommendVo noticeRecommendVo)
	{
		recommendSQLMapeer.deleteRecommend(noticeRecommendVo.getBnr_no());
		System.out.println("[System] deleteRecommend");
	}
	
	public NoticeRecommendVo isRecommendClicked(NoticeRecommendVo noticeRecommendVo)
	{
		return recommendSQLMapeer.isRecommendCliked(noticeRecommendVo);
	}
}
