package com.give.donagi.freeboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.give.donagi.freeboard.mapper.FreeBoardLikeSQLMapper;
import com.give.donagi.vo.BFLikeVo;

@Service
public class FreeBoardLikeServiceImpl {

	@Autowired
	private FreeBoardLikeSQLMapper freeBoardLikeSqlMapper;
	
	
	// 추천
	public void like(BFLikeVo vo) {
		int bfl_no = this.freeBoardLikeSqlMapper.createLno();
		
		vo.setBfl_no(bfl_no);
		freeBoardLikeSqlMapper.doLike(vo);
	}
	
	
	// 추천 취소
	public void cancelLike(BFLikeVo vo) {
		
		freeBoardLikeSqlMapper.cancelLike(vo);
	}
	
	
	// 추천 수 카운트
	public void countLike(int bf_no) {
		
		freeBoardLikeSqlMapper.countLike(bf_no);
	}
	
	// 추천 중복체크
	public void checkLike(BFLikeVo vo) {
		
		freeBoardLikeSqlMapper.checkLike(vo);
	}
	
	
}
