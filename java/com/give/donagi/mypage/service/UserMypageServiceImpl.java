package com.give.donagi.mypage.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.give.donagi.funding.mapper.UserFundingSQLMapper;
import com.give.donagi.mypage.mapper.UserMypageSQLMapper;
import com.give.donagi.vo.MRequestComVo;
import com.give.donagi.vo.MSTFundingVo;
import com.give.donagi.vo.MemberVo;
import com.give.donagi.vo.OrderFVo;

@Service
public class UserMypageServiceImpl {
	@Autowired
	private UserMypageSQLMapper mypageMapper;
	@Autowired
	private UserFundingSQLMapper fundingMapper;
	
	public int existRequestCom(HashMap<String, Object> loginUser) {
		int result = 0;
		ArrayList<MRequestComVo> resultList = this.mypageMapper.selectRequestComByMap(((MemberVo)loginUser.get("memberVo")).getM_no());
		
		if(resultList.size() <= 0) {
		
			result = 0;
			
		}else if(resultList.size() > 0) {
			
			result = 1;
		}
		
		return result;
	}
	
	public void requestCom(MRequestComVo mRequestComVo, HashMap<String, Object> loginUser) {
		
		mRequestComVo.setMrc_no(this.mypageMapper.createRequestNo());
		mRequestComVo.setM_no(((MemberVo)loginUser.get("memberVo")).getM_no());
		
		this.mypageMapper.insertRequestCom(mRequestComVo);
		
		System.out.println("[System] requestCom");
	}
	
public void deleteOrder(int of_no) {
		
		this.mypageMapper.deleteOrderByNo(of_no);
		this.mypageMapper.deleteOrderDetailByOrderNo(of_no);
		
		System.out.println("[System] deleteOrder");
	}
	
	public void updateOrder(OrderFVo orderFVo) {
		
		this.mypageMapper.updateOrder(orderFVo);
		
		System.out.println("[System] updateOrder");
	}
	
	public void updateFundingStatus(int mst_f_no, int mst_fs_no) {
		ArrayList<OrderFVo> orderList = null;
		MSTFundingVo mstFundingVo = new MSTFundingVo();
		
		mstFundingVo.setMst_f_no(mst_f_no);
		mstFundingVo.setMst_fs_no(mst_fs_no);
		orderList = this.fundingMapper.selectOrderListByFundingNo(mstFundingVo.getMst_f_no());
		
		this.mypageMapper.updateFundingStatus(mstFundingVo);
		
		for(OrderFVo order: orderList) {
			
			if(mstFundingVo.getMst_fs_no() == 1) { // 제작시작
				
				order.setMst_os_no(1);
				
			}else if(mstFundingVo.getMst_fs_no() == 2) { // 제작완료
				
				order.setMst_os_no(2);
			
			}else if(mstFundingVo.getMst_fs_no() == 3) { // 배송시작
				
				order.setMst_os_no(3);
			}
			
			this.mypageMapper.updateOrderStatus(order);
		}
		
		System.out.println("[System] updateFundingStatus");
	}
	
	public void updateDeliveryStatus(int of_no, int mst_f_no) {
		OrderFVo order = new OrderFVo();
		ArrayList<OrderFVo> orderList = null;
		boolean fundingStatusFlg = false;
		
		order.setOf_no(of_no);
		order.setMst_os_no(4);
		
		this.mypageMapper.updateDeliveryStatus(order);
		
		orderList = this.fundingMapper.selectOrderListByFundingNo(mst_f_no);
		
		for(OrderFVo orderVo: orderList) {
			
			if(orderVo.getMst_os_no() < 4) {
				
				fundingStatusFlg = true;
			}
		}
		
		if(!fundingStatusFlg) {
			MSTFundingVo mstFundingVo = this.fundingMapper.selectFundingByNo(mst_f_no);
			
			mstFundingVo.setMst_fs_no(4);
			
			this.mypageMapper.updateFundingStatus(mstFundingVo);
		}
		
		System.out.println("[System] updateDeliveryStatus");
	}
}
