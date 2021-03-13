package com.give.donagi.mypage.mapper;

import java.util.*;
import com.give.donagi.vo.*;

public interface UserMypageSQLMapper {
	public int createRequestNo();
	public void insertRequestCom(MRequestComVo mRequestComVo);
	public ArrayList<MRequestComVo> selectRequestComByMap(int m_no);
	public void deleteOrderByNo(int of_no);
	public void deleteOrderDetailByOrderNo(int of_no);
	public void updateOrder(OrderFVo orderFvo);
	public void updateFundingStatus(MSTFundingVo mstFundingVo);
	public void updateOrderStatus(OrderFVo orderFVo);
	public void updateDeliveryStatus(OrderFVo orderFVo);
}
