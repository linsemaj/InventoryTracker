package com.example.InventoryTracker.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.InventoryTracker.Domain.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{
	
	@Query("select s from Store s where user_Id= :userId")
	Set<Store> findByUserId(long userId);

	@Query("select s from Store s WHERE user_Id= :userId"
			+ " and (s.color = :red or s.color= :green)")
	Set<Store> findStoreAlertsByUserId(long userId,String red,String green);

}
