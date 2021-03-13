package com.give.donagi.freeboard.mapper;

import java.util.ArrayList;

import com.give.donagi.vo.BoardFreeVo;
import com.give.donagi.vo.MSTBFHeadlineVo;


public interface FreeBoardSQLMapper {

	public int createKey();
	
	public void insertFreeBoard(BoardFreeVo vo);
	
	public ArrayList<BoardFreeVo> selectFreeBoard(int page_no);
	
	public void viewCount(int no); // 조회수
	
	public BoardFreeVo selectByNo(int no);
	
	public void deleteFreeBoard(int no);
	
	public void updateFreeBoard(BoardFreeVo vo);
	
	public int getPageCount();
	
	
	// 검색
	public ArrayList<BoardFreeVo> searchTitle(String search_word);
	
	public ArrayList<BoardFreeVo> searchContent(String search_word);
	
	public ArrayList<BoardFreeVo> searchWriter(String search_word);
	
	
	// 헤드라인
	public void insertHeadline(MSTBFHeadlineVo vo);
	
	public MSTBFHeadlineVo selectHeadline(int no);

	
	// 댓글 수 출력
	public int selectCommentCnt(int bf_no);
	
}
