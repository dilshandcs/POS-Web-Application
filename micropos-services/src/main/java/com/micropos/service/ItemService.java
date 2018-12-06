package com.micropos.service;

import java.util.List;

import com.micropos.dto.ItemDto;
import com.micropos.dto.ItemDtoList;

public interface ItemService {
	List<ItemDto> searchStock(String query);
	ItemDto getPartDetailsByItemNumber(String itemNumber);
	List<ItemDto> getAllItems();
	void updateItems(ItemDtoList itemDtoList);
	void removeAnItem(Integer itemId);
}
