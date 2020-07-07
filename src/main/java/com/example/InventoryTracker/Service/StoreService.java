package com.example.InventoryTracker.Service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.InventoryTracker.Domain.Store;
import com.example.InventoryTracker.Repository.StoreRepository;

@Service
public class StoreService {

	@Autowired
	StoreRepository storeRepo;

	public Set<Store> findByUserId(long userId) {
		return storeRepo.findByUserId(userId);
	}

	public void save(Store store) {
		storeRepo.save(store);
	}

	public Store findById(long storeId) {
		return storeRepo.findById(storeId).orElse(new Store());
	}

	public Set<Store> findStoreAlertsByUserId(long userId) {
		// TODO Auto-generated method stub
		return storeRepo.findStoreAlertsByUserId(userId, "red", "yellow");
	}

}
