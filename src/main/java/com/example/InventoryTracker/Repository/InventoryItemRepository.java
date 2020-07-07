package com.example.InventoryTracker.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.InventoryTracker.Domain.InventoryItem;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long>{

	@Query("select i from InventoryItem i where store_store_Id= :storeId and (color='yellow' or color='red')")
	List<InventoryItem> findItemAlertsByStoreId(Long storeId);

}
