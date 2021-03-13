package com.give.donagi.donate.mapper;

import java.util.*;
import com.give.donagi.vo.*;

public interface UserDonateSQLMapper {

	public int createMSTDNo(); // 기부 키 생성
	public ArrayList<MSTDCategoryVo> selectMSTDCategory(); // 카테고리 정보 받기
	public MSTDCategoryVo selectCategoryByNo(int mst_dc_no); // 카테고리 받기
	public void insertMSTDonate(MSTDonateVo mstDonateVo); // 기부 정보 입력
	public int createMSTDoNo(); // 기부 옵션 키 생성
	public void insertDonateImg(MSTDImgVo mstDImgVo); // 기부 썸네일 생성
	public int searchForPageCount(HashMap<String, Object> searchMap); // 페이지수 출력을 위한 리스트 받기
	public ArrayList<MSTDonateVo> selectDonateList(HashMap<String, Object> searchMap); // 기부 리스트 받기
	public MSTDonateVo selectDonateByNo(int mst_d_no); // 기부 정보 받기
	public MSTDImgVo selectThumnailByDNo(int mst_d_no); // 기부 썸네일 받기
	public int createOrderDetailNo(); // 주분상세번호 생성
	public void insertDonateD(DonateDVo vo);
	public ArrayList<DonateDVo> selectPointByMSTDNo(int mst_d_no);
}
