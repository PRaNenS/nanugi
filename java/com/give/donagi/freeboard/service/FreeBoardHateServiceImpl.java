package com.give.donagi.freeboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.give.donagi.freeboard.mapper.FreeBoardHateSQLMapper;
import com.give.donagi.vo.BFHateVo;

@Service
public class FreeBoardHateServiceImpl {

	@Autowired
	private FreeBoardHateSQLMapper freeBoardHateSqlMapper;
	
	// 비추천
	public void hate(BFHateVo vo) {
		int bfh_no = this.freeBoardHateSqlMapper.createHno();
		
		vo.setBfh_no(bfh_no);
		freeBoardHateSqlMapper.doHate(vo);	
	}
	
	// 비추천 취소
	public void cancelHate(BFHateVo vo) {
		
		freeBoardHateSqlMapper.dontHate(vo);
	}
	
	
	// 비추천 수 카운트
	public void countHate(int bf_no) {
		
		freeBoardHateSqlMapper.countHate(bf_no);
	}
	
	// 비추천 중복체크
	public void checkHate(BFHateVo vo) {
		
		freeBoardHateSqlMapper.checkHate(vo);
	}
	
}
