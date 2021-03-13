package com.give.donagi.freeboard.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.give.donagi.freeboard.mapper.FreeBoardCommentSQLMapper;
import com.give.donagi.freeboard.mapper.FreeBoardHateSQLMapper;
import com.give.donagi.freeboard.mapper.FreeBoardImageSQLMapper;
import com.give.donagi.freeboard.mapper.FreeBoardLikeSQLMapper;
import com.give.donagi.freeboard.mapper.FreeBoardReportSQLMapper;
import com.give.donagi.freeboard.mapper.FreeBoardSQLMapper;
import com.give.donagi.manager.mapper.ManagerSQLMapper;
import com.give.donagi.member.mapper.UserMemberSQLMapper;
import com.give.donagi.vo.BFHateVo;
import com.give.donagi.vo.BFImgLinkVo;
import com.give.donagi.vo.BFLikeVo;
import com.give.donagi.vo.BoardFreeVo;
import com.give.donagi.vo.CommentBFVo;
import com.give.donagi.vo.MSTBFHeadlineVo;
import com.give.donagi.vo.MemberVo;
import com.give.donagi.vo.ReportBFVo;

@Service
public class FreeBoardServiceImpl {

	@Autowired
	private FreeBoardSQLMapper freeBoardSqlMapper;
	
	@Autowired
	private UserMemberSQLMapper userMemberSqlMapper;
	
	@Autowired
	private ManagerSQLMapper managerSQLMapper;
	
	@Autowired
	FreeBoardCommentSQLMapper freeBoardCommentSqlMapper;
	
	@Autowired
	FreeBoardImageSQLMapper freeBoardImageSqlMapper;
	
	@Autowired
	FreeBoardLikeSQLMapper freeBoardLikeSqlMapper;
	
	@Autowired
	FreeBoardHateSQLMapper freeBoardHateSqlMapper;
	
	@Autowired
	FreeBoardReportSQLMapper freeBoardReportSqlMapper;
	
	
	// 자유게시판 리스트 가져오기 (+ 검색 기능)
	public ArrayList<HashMap<String, Object>> getFreeBoardList(String search_word, String search_type, int page_no){
		ArrayList<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
	
		ArrayList<BoardFreeVo> freeBoardList = null;
		
		// 검색
		if(search_word == null || search_type == null) {
			freeBoardList = freeBoardSqlMapper.selectFreeBoard(page_no);
		}else {
			if(search_type.equals("title")) {
				freeBoardList = freeBoardSqlMapper.searchTitle(search_word);
			}else if(search_type.equals("content")){
				freeBoardList = freeBoardSqlMapper.searchContent(search_word);
			}else if(search_type.equals("writer")) {
				freeBoardList = freeBoardSqlMapper.searchWriter(search_word);
			}else {
				freeBoardList = freeBoardSqlMapper.selectFreeBoard(page_no);
			}
		}
		
		for(BoardFreeVo freeBoardVo : freeBoardList) {
			int m_no = freeBoardVo.getM_no();
			MemberVo memberVo = userMemberSqlMapper.selectByNo(m_no);
			
			MSTBFHeadlineVo freeBoardHeadlineVo = new MSTBFHeadlineVo();
			freeBoardHeadlineVo = freeBoardSqlMapper.selectHeadline(freeBoardVo.getMst_bfh_no());
				
			int commentCnt = freeBoardSqlMapper.selectCommentCnt(freeBoardVo.getBf_no());
			
			ArrayList<BFImgLinkVo> imgList = freeBoardImageSqlMapper.selectImage(freeBoardVo.getBf_no());
			
			int countLikeVo = freeBoardLikeSqlMapper.countLike(freeBoardVo.getBf_no());
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("memberVo", memberVo);
			map.put("freeBoardVo", freeBoardVo);
			map.put("freeBoardHeadlineVo", freeBoardHeadlineVo);
			map.put("commentCnt", commentCnt);
			map.put("imgList", imgList);
			map.put("countLikeVo", countLikeVo);
			
			resultList.add(map);
		
		}
		
		return resultList;
		
	}
	
	// 글 상세보기 페이지 가져오기 ////////////// 요기
	public HashMap<String, Object> getFreeBoard(int freeboardNo, HashMap<String, Object>loginUser){
		
		
		// 조회수 증가
		freeBoardSqlMapper.viewCount(freeboardNo);
		
		// 글 출력데이터 가져오기
		BoardFreeVo freeBoardVo = freeBoardSqlMapper.selectByNo(freeboardNo);
		
		
		freeBoardVo.getM_no();
		int m_no = freeBoardVo.getM_no();
		MemberVo memberVo = userMemberSqlMapper.selectByNo(m_no);
		
		// 말머리 가져오기
		MSTBFHeadlineVo freeBoardHeadlineVo = freeBoardSqlMapper.selectHeadline(freeBoardVo.getMst_bfh_no());
		//int bfh_no = freeBoardHeadlineVo.getMst_bfh_no();
		
		// 이미지 가져오기
		ArrayList<BFImgLinkVo> imgList = freeBoardImageSqlMapper.selectImage(freeboardNo);
		
		MemberVo loginData = (MemberVo)loginUser.get("memberVo");
		
		m_no = loginData.getM_no();
		
		
		// 추천
		BFLikeVo bfLikeVo = new BFLikeVo();
		bfLikeVo.setM_no(m_no);
		bfLikeVo.setBf_no(freeboardNo);
		
		BFLikeVo likeVo = freeBoardLikeSqlMapper.checkLike(bfLikeVo);
		
		// 추천 취소
		BFLikeVo bfLikeVo2 = new BFLikeVo();
		bfLikeVo2.setM_no(m_no);
		bfLikeVo2.setBf_no(freeboardNo);
		
		BFLikeVo likeCancelVo = freeBoardLikeSqlMapper.checkLike(bfLikeVo2);

		
		// 추천수 가져오기
		BFLikeVo bfLikeVo3 = new BFLikeVo();
		
		BoardFreeVo likeBoardVo = new BoardFreeVo();
		likeBoardVo.getBf_no();
		bfLikeVo3.setBf_no(freeboardNo);
		
		int countLikeVo = freeBoardLikeSqlMapper.countLike(freeboardNo);
		
		
		// 비추천 가져오기
		BFHateVo bfHateVo = new BFHateVo();
		bfHateVo.setM_no(m_no);
		bfHateVo.setBf_no(freeboardNo);
		
		BFHateVo hateVo = freeBoardHateSqlMapper.checkHate(bfHateVo);
		
		// 비추천 취소 가져오기
		BFHateVo bfHateVo2 = new BFHateVo();
		bfHateVo2.setM_no(m_no);
		bfHateVo2.setBf_no(freeboardNo);
		
		BFHateVo hateCancelVo = freeBoardHateSqlMapper.checkHate(bfHateVo2);
		
		
		// 비추천수 가져오기
		BFHateVo bfHateVo3 = new BFHateVo();
		
		BoardFreeVo hateBoardVo = new BoardFreeVo();
		hateBoardVo.getBf_no();
		bfHateVo3.setBf_no(freeboardNo);
		
		int countHateVo = freeBoardHateSqlMapper.countHate(freeboardNo);
		
		
		// 전체 출력
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memberVo", memberVo);
		map.put("freeBoardVo", freeBoardVo);
		map.put("freeBoardHeadlineVo", freeBoardHeadlineVo);
		map.put("BFImgLinkVo", imgList);
		map.put("likeVo", likeVo);
		map.put("likeCancelVo", likeCancelVo);
		map.put("countLikeVo", countLikeVo);
		map.put("hateVo", hateVo);	
		map.put("hateCancelVo", hateCancelVo);
		map.put("countHateVo", countHateVo);
		
		// 코멘트
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
		
		ArrayList<CommentBFVo> commentList = freeBoardCommentSqlMapper.selectComment(freeboardNo);
			
		for(CommentBFVo freeBoardCommentVo : commentList) {
			MemberVo commentMemberVo = userMemberSqlMapper.selectByNo(freeBoardCommentVo.getM_no());
				
			HashMap<String, Object> commentMap = new HashMap<String, Object>();
			commentMap.put("memberVo", commentMemberVo);
			commentMap.put("freeBoardCommentVo", freeBoardCommentVo);
				
			result.add(commentMap);
			
		}
		
		map.put("commentList", result);
		
		
		return map;
	}
	
	// 게시판 글 작성
	public void writeFreeBoard(BoardFreeVo vo, ArrayList<BFImgLinkVo> imageVoList) {
		
		int pk = freeBoardSqlMapper.createKey();
		vo.setBf_no(pk);
		freeBoardSqlMapper.insertFreeBoard(vo);
		
		for(BFImgLinkVo imgVo : imageVoList) {
			imgVo.setBf_no(pk);
			freeBoardImageSqlMapper.uploadImage(imgVo);
			System.out.println(imgVo.getBfil_path());
		}
	}
	
	// 페이징
	public int getFreeBoardPageCount() {
		return freeBoardSqlMapper.getPageCount();
	}
	
	
	// 수정
	public void updateFreeBoard(BoardFreeVo vo) {
		freeBoardSqlMapper.updateFreeBoard(vo);
	}
	
	
	// 삭제
	public void deleteFreeBoard(int no) {
		freeBoardSqlMapper.deleteFreeBoard(no);
	}

	
	/* 신고 기능 */
	
	// 자유게시판 글 신고
	public void writeReport(ReportBFVo vo) {
		freeBoardReportSqlMapper.insertReport(vo);
	}
	

	// 관리자 > 자유게시판 신고 main
	public ArrayList<HashMap<String, Object>> getReportList(int current_page){
		ArrayList<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();

		ArrayList<ReportBFVo> reportList = null;
		
		reportList = freeBoardReportSqlMapper.selectReportAll(current_page);
		
		
		for(ReportBFVo reportBfVo : reportList) {
			int m_no = reportBfVo.getM_no();
			MemberVo memberVo = userMemberSqlMapper.selectByNo(m_no);
			int confirm = managerSQLMapper.isPenalty(m_no);
			
			int bf_no = reportBfVo.getBf_no();
			BoardFreeVo bfvo = freeBoardSqlMapper.selectByNo(bf_no);
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			// 전송 내역
			map.put("reportBfVo", reportBfVo);
			map.put("bfvo", bfvo);
			map.put("memberVo", memberVo);
			map.put("confirm",confirm);
		
			
			resultList.add(map);
			
		}
		
		return resultList;
	}
	
	
	public int getReportPageCount() {
		return freeBoardReportSqlMapper.getReportPageCount();
	}
	
}