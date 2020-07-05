package com.example.InventoryTracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.InventoryTracker.Domain.InventoryItem;
import com.example.InventoryTracker.Repository.InventoryItemRepository;

@Service
public class InventoryItemService {

	@Autowired
	InventoryItemRepository itemRepo;

	public void save(InventoryItem item) {
		itemRepo.save(item);
	}

	public InventoryItem findById(long itemId) {
		return itemRepo.findById(itemId).orElse(new InventoryItem());
	}

	public void delete(InventoryItem item) {
		itemRepo.delete(item);
	}
}
