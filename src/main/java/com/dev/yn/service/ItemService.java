package com.dev.yn.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.yn.domain.item.Item;
import com.dev.yn.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
	
	private final ItemRepository itemRepository;
	
	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	
	public List<Item> findItems() {
		return itemRepository.findAll();
	}
	
	public Item findOneItem(Long itemId) {
		return itemRepository.findOne(itemId);
	}
}
