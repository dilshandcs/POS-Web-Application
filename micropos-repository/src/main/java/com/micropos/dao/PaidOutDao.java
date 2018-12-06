package com.micropos.dao;


import java.util.List;

import com.micropos.domain.PaidOut;

public interface PaidOutDao extends BaseDao<PaidOut>{
	 void SavePaidOut(PaidOut paidOut);
	 List<PaidOut> getAllPaidOuts();
}
