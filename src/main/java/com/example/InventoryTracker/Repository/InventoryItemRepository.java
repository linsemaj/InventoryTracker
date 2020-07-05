package com.example.InventoryTracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.InventoryTracker.Domain.InventoryItem;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long>{

}
