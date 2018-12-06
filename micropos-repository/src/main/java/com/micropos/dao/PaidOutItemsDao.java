package com.micropos.dao;

import java.util.List;

import com.micropos.domain.PaidOutItems;

public interface PaidOutItemsDao extends BaseDao<PaidOutItems>{
	 List<PaidOutItems> getAllPaidOutItems();
}
