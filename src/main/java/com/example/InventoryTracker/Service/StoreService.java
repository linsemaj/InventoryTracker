package com.example.InventoryTracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.InventoryTracker.Repository.StoreRepository;

@Service
public class StoreService {

	@Autowired
	StoreRepository StoreRepo;
}
