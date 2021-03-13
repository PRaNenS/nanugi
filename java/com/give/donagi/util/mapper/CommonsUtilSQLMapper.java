package com.give.donagi.util.mapper;

import java.util.*;
import com.give.donagi.vo.*;

public interface CommonsUtilSQLMapper {
	
	public BEImgVo selectEventBanner1();
	public BEImgVo selectEventBanner2();
	public BEImgVo selectEventBanner3();
	public MPointVo lookupPoint(int m_no);
	public ArrayList<MRecordPVo> getMRecordP(HashMap<String, Object> map);
	public void updatePoint(MPointVo mPointVo);
	public int createMRecordPKey();
	public void insertRecord(MRecordPVo mRecordPVo);
}
