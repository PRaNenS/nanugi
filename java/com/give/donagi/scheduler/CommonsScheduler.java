package com.give.donagi.scheduler;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.give.donagi.manager.mapper.ManagerSQLMapper;
import com.give.donagi.vo.MLimitVo;

@Component
public class CommonsScheduler {
	@Autowired
	private ManagerSQLMapper managerSQLMapper;

	@Scheduled(cron = "0 0 0 * * *")
	public void updatePenalty()
	{
		Date currentDate = new Date(); // 현재시간
		ArrayList<MLimitVo> limitList = this.managerSQLMapper.selectLimitAll();
		
		for(MLimitVo limitvo : limitList)
		{
			Date ml_date = limitvo.getMl_date();
			
			int m_no = limitvo.getM_no();
			
			int compare = ml_date.compareTo(currentDate);
			
			if(compare<0)
			{
				this.managerSQLMapper.deleteLimit(m_no);
			}
			
			System.out.println(compare);
		}
	}
}
