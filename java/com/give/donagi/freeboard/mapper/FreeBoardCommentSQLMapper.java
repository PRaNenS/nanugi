package com.give.donagi.freeboard.mapper;

import java.util.ArrayList;

import com.give.donagi.vo.CommentBFVo;

public interface FreeBoardCommentSQLMapper {
	
	public int createCno(); //seq.nextval

	public void insertComment(CommentBFVo vo);
	
	public ArrayList<CommentBFVo> selectComment(int no);
	
	public void updateComment(CommentBFVo vo);
	
	public void deleteComment(int no);
	
	public int countComment(int no);
	
	
}
