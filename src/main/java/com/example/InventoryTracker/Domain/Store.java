package com.example.InventoryTracker.Domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "stores")
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long storeId;
	@OneToMany(mappedBy="store",orphanRemoval = true)
	private List<InventoryItem> items;
	private String storeName;
	@ManyToOne
	private User user;
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
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
	public void addItem(InventoryItem item) {
		this.items.add(item);
	}
	
	
}
