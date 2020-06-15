package com.example.InventoryTracker.Domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Store {
	@Id 
	private Long storeId;
	@OneToMany
	private List<InventoryItem> items;
	@ManyToOne()
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public List<InventoryItem> getItems() {
		return items;
	}
	public void setItems(List<InventoryItem> items) {
		this.items = items;
	}
	
	
}
