package com.give.donagi.notice.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.give.donagi.notice.mapper.RecommendSQLMapper;
import com.give.donagi.notice.service.UserNoticeServiceImpl;
import com.give.donagi.vo.MemberVo;
import com.give.donagi.vo.NoticeRecommendVo;

@Controller
@ResponseBody
@RequestMapping("/notice/*")
public class RestFulCommentController 
{
	@Autowired
	private UserNoticeServiceImpl UserNoticeService;
	
	@Autowired
	private RecommendSQLMapper recommendSQLMapper;
	
	@RequestMapping("recommendProcess.do")
	public HashMap<String, Object> recommendProcess(Model model,  NoticeRecommendVo noticeRecommendVo,HttpSession session)
	{
		
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		int result = 1;
		
		NoticeRecommendVo Vo = new NoticeRecommendVo();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		MemberVo vo = (MemberVo) loginUser.get("memberVo");
		int m_no = vo.getM_no();	
		noticeRecommendVo.setM_no(m_no);
		
		Vo = UserNoticeService.isRecommendClicked(noticeRecommendVo);
		
		if(Vo == null)
		{
			UserNoticeService.insertRecommend(noticeRecommendVo);
			 
			result = 1;
		}
		else
		{
			UserNoticeService.deleteRecommend(noticeRecommendVo);
			 result = 0;
		}
		
		resultMap.put("result", result);
		
		return resultMap;
	}
}
