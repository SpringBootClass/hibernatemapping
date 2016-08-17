package com.microservices.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.item.dao.ItemDaoIF;
import com.microservices.item.exception.ItemException;
import com.microservices.item.model.Item;

@Service
public class ItemServiceImpl implements ItemServiceIF {
	@Autowired
	private ItemDaoIF itemDaoIF;
	
	@Override
	public List<Item> getItems() throws ItemException {
		return itemDaoIF.getItems();
	}

	@Override
	public Object getItem(Long itemId) throws ItemException {
		return itemDaoIF.getItem(itemId);
	}
	
	@Override
	public Object saveItem(Item item) throws ItemException {
		return itemDaoIF.saveItem(item);
	}	

	public ItemDaoIF getItemDaoIF() {
		return itemDaoIF;
	}

	public void setItemDaoIF(ItemDaoIF employeeDaoIF) {
		this.itemDaoIF = itemDaoIF;
	}


}
