package com.micropos.dao;

import java.util.List;

import com.micropos.domain.Items;

public interface ItemDao extends BaseDao<Items>{
	List<Items> searchParts(String query);
	Items getPartDetailsByItemNumber(String itemNumber);
	List<Items> getAllActiveItems();
	Items getItemById(Integer itemId);
	Items getItemByName(String name);
}
