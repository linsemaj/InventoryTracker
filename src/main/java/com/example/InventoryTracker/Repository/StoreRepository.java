package com.example.InventoryTracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.InventoryTracker.Domain.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{

}
