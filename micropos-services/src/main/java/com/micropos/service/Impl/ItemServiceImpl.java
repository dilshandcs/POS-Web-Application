package com.micropos.service.Impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micropos.dao.ItemDao;
import com.micropos.domain.Items;
import com.micropos.dto.ItemDto;
import com.micropos.dto.ItemDtoList;
import com.micropos.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	ItemDao itemDao;

	@Transactional
	public List<ItemDto> searchStock(String query) {
		List<ItemDto> resultList=new ArrayList<ItemDto>();
		List<Items> listOfItems = itemDao.searchParts(query);

		Iterator<Items> itemIterator=listOfItems.iterator();
		while(itemIterator.hasNext()) {
			Items item=itemIterator.next();
			ItemDto itemDto=new ItemDto();
			itemDto.setId(item.getId());
			itemDto.setItemNumber(item.getItemNumber());
			itemDto.setName(item.getName());
			itemDto.setPrice(item.getPrice());
			itemDto.setIsDelete(item.getIsDelete());
			itemDto.setDisplayValue(item.getItemNumber()+" - "+item.getName());

			resultList.add(itemDto);
		}
		return 	resultList;
	}
	@Transactional
	public ItemDto getPartDetailsByItemNumber(String itemNumber) {
		Items item=itemDao.getPartDetailsByItemNumber(itemNumber);
		ItemDto itemDto=new ItemDto();
		if(item!=null) {
			itemDto.setId(item.getId());
			itemDto.setItemNumber(item.getItemNumber());
			itemDto.setName(item.getName());
			itemDto.setPrice(item.getPrice());
			itemDto.setIsDelete(item.getIsDelete());
		}
		return itemDto;
	}

	@Transactional
	public List<ItemDto> getAllItems() {
		List<Items> items=itemDao.getAllActiveItems();
		List<ItemDto> resultList=new ArrayList<ItemDto>();
		Iterator<Items> itemIterator=items.iterator();
		while(itemIterator.hasNext()) {
			Items item=itemIterator.next();
			ItemDto itemDto=new ItemDto();
			itemDto.setId(item.getId());
			itemDto.setItemNumber(item.getItemNumber());
			itemDto.setName(item.getName());
			itemDto.setPrice(item.getPrice());
			resultList.add(itemDto);
		}
		return resultList;
	}

	@Transactional
	public void updateItems(ItemDtoList itemDtoList) {
		for (ItemDto itemDto : itemDtoList.getItemDtoList()) {
			if (itemDto.getName() == null || itemDto.getName().isEmpty()) {
				continue;
			}
			if (itemDto.getId() == null) {
				Items item = itemDao.getItemByName(itemDto.getName());
				if (item != null && item.getIsDelete() == false) {
					throw new RuntimeException("Item name already exists");
				} else if (item != null && item.getIsDelete()) {
					item.setPrice(itemDto.getPrice());
					item.setIsDelete(false);
				} else {
					item = new Items();
					item.setName(itemDto.getName());
					item.setIsDelete(false);
					item.setPrice(itemDto.getPrice());
					itemDao.create(item);
					String itemNumber=String.format("I%03d", item.getId());
					item.setItemNumber(itemNumber);
				}
			} else {
				Items itemById = itemDao.getItemById(itemDto.getId());
				if (itemById.getName().equals(itemDto.getName())) {
					itemById.setPrice(itemDto.getPrice());
					String itemNumber=String.format("I%03d", itemDto.getId());
					itemById.setItemNumber(itemNumber);

					} else {
						itemById.setName(itemDto.getName());
						itemById.setIsDelete(false);
						itemById.setPrice(itemDto.getPrice());
						String itemNumber=String.format("I%03d", itemDto.getId());
						itemById.setItemNumber(itemNumber);
					}
				}

		}

	}

	@Transactional
	public void removeAnItem(Integer itemId) {
		Items item = itemDao.getItemById(itemId);
		if ((item == null)) {
			throw new RuntimeException("Item doesn't exist");
		}
		item.setIsDelete(true);

	}


}
