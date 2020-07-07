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
	private String color;
	@ManyToOne
	private User user;
	
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setDefaultColor() {
		Integer lowestColor= 3;
		if(null!=items&&!items.isEmpty()) {
			for(InventoryItem item:items) {
				if(lowestColor>2 && item.getColor()=="yellow") lowestColor= Math.min(2, lowestColor);
				if(lowestColor>1 && item.getColor()=="red") lowestColor= Math.min(1, lowestColor);
			}			
		}
		if(lowestColor==3) color="lawngreen";
		if(lowestColor==2) color="yellow";
		if(lowestColor==1) color="red";
	}
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
		if(items.contains(item)) {
			items.set(items.indexOf(item), item);
		}
		else {
			this.items.add(item);			
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		if (storeId == null) {
			if (other.storeId != null)
				return false;
		} else if (!storeId.equals(other.storeId))
			return false;
		return true;
	}
	
	
}
