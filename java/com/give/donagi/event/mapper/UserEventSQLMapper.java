package com.give.donagi.event.mapper;

import java.util.*;
import com.give.donagi.vo.*;

public interface UserEventSQLMapper {

	public int createEventKey();
	public void createEvent(BEventVo bEventVo);
	public int createImgKey();
	public void insertEventImg(BEImgVo beImgVo);
	public int getPageCount(HashMap<String, Object> searchMap);
	public ArrayList<BEventVo> selectAllEvent(HashMap<String, Object> searchMap);
	public BEImgVo selectImgByEventNo(int be_no);
	public BEventVo selectEventByNo(int be_no);
	public void deleteEvent(int be_no);
	public int createCommentKey();
	public void writeComment(CommentBEVo commentBEVo);
	public ArrayList<CommentBEVo> selectAllCommentByEventNo(int be_no);
	public void deleteComment(int cbe_no);
}
