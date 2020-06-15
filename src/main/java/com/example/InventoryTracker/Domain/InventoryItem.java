package com.example.InventoryTracker.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "items")
public class InventoryItem {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;
	private Integer quantity;
	private String name;
	private Integer lowCount;
	@ManyToOne
	private Store store;
	
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLowCount() {
		return lowCount;
	}
	public void setLowCount(Integer lowCount) {
		this.lowCount = lowCount;
	}

	
}
