package com.example.InventoryTracker.web;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.InventoryTracker.Domain.InventoryItem;
import com.example.InventoryTracker.Domain.Store;
import com.example.InventoryTracker.Domain.User;
import com.example.InventoryTracker.Service.InventoryItemService;
import com.example.InventoryTracker.Service.StoreService;
import com.example.InventoryTracker.Service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	StoreService storeService;
	@Autowired
	InventoryItemService itemService;
	
	@GetMapping("/")
	public String start(){
		return "redirect:/user/signIn";
	}
	@GetMapping("/user/signIn")
	public String signUp(ModelMap model){
		model.put("user", new User());
		return "signIn";
	}
	@ResponseBody
	@PostMapping("/user/signup")
	public String verifyAndSignup(@RequestBody User user){
		 User userWithEmail=userService.checkEmail(user);
		 User userWithName=userService.checkUsername(user);
		 if(userWithEmail!= null) return userWithEmail.getEmail();
		 if(userWithName!= null) return userWithName.getUsername();
		userService.save(user);
		return "Signed up";
	}
	@ResponseBody
	@PostMapping("/user/login")
	public Boolean verifyAndLogin(@RequestBody User user){
		User foundUser=userService.checkLoginInputs(user);
		if(!(foundUser==null)) return true;
		return false;
	}
	@PostMapping("/user/home")
	public String usertestHome(User user){
		user = userService.checkLoginInputs(user);
		if(user==null) return "redirect:/user/signup";
		return "redirect:/user/"+user.getId()+"/home";
	}
	@GetMapping("/user/{userId}/home")
	public String userHome(@PathVariable long userId, ModelMap model){
		User user = userService.findById(userId);
		Set<Store> userStores = storeService.findByUserId(userId);
		model.put("user", user);
		model.put("stores", userStores);
		return "home";
	}
	@ResponseBody
	@PostMapping("/user/{userId}/addStore")
	public Long store(@RequestBody String storeName, @PathVariable long userId){
		Store store = new Store();
		store.setDefaultColor();
		User user = userService.findById(userId);
		store.setStoreName(storeName);
		user.addStore(store);
		store.setUser(user);
		storeService.save(store);
		return store.getStoreId();
	}
	@ResponseBody
	@GetMapping("/user/{userId}/home/alert")
	public String alertUser(@PathVariable long userId){
		String returnString ="";
		Set<Store> alertStores = storeService.findStoreAlertsByUserId(userId);
		for(Store store:alertStores) {
			returnString+= "From Store: "+store.getStoreName()+ " \n";
			List<InventoryItem> items = itemService.findItemAlertsByStoreId(store.getStoreId());
			for(InventoryItem item:items) {
				returnString += ""+item.getName()+": Status="+ item.getColor()+" Quantity: "+item.getQuantity()+"\n";
			}
			returnString += "\n";
		}
		return returnString;
	}
	@ResponseBody
	@PostMapping("/user/{userId}/store/{storeId}/item/{itemId}/change1")
	public void store(@PathVariable long userId,@PathVariable long storeId,@PathVariable long itemId, @RequestBody Integer one){
		Store store =storeService.findById(storeId);
		InventoryItem item = itemService.findById(itemId);
		item.setQuantity(item.getQuantity()+one);
		item.setDefaultColor();
		store.addItem(item);
		store.setDefaultColor();
		storeService.save(store);
		itemService.save(item);
	}
	
	@GetMapping("/user/{userId}/store/{storeId}/items")
	public String userStore(@PathVariable long userId,@PathVariable long storeId, ModelMap model){
		User user = userService.findById(userId);
		if(user.getId()==null) {
			model.put("items", new InventoryItem());
			model.put("user", new User());
			model.put("store", new Store());
			return "redirect:/user/signIn";
		}
		Store store = storeService.findById(storeId);
		List<InventoryItem> items = store.getItems();
		model.put("items", items);
		model.put("user", user);
		model.put("store", store);
		return "store";
	}
	@PostMapping("/user/{userId}/store/{storeId}/items")
	public String itemUpdate(@PathVariable long userId,@PathVariable long storeId, InventoryItem item){
		saveNewOrEditedItem(storeId, item);
		return "redirect:/user/"+userId+"/store/"+storeId+"/items";
	}
	@PostMapping("/user/{userId}/store/{storeId}/items/{itemId}/delete")
	public String deleteItem(@PathVariable long userId,@PathVariable long storeId,@PathVariable long itemId, @RequestBody Long sentItemId){
		InventoryItem item = itemService.findById(sentItemId);
		itemService.delete(item);
		return "redirect:/user/"+userId+"/store/"+storeId+"/items";
	}
	@PostMapping("/user/{userId}/store/{storeId}/items/{itemId}/edit")
	public String editItem(@PathVariable long userId,@PathVariable long storeId, @PathVariable long itemId, InventoryItem item){
		saveNewOrEditedItem(storeId, item);
		return "redirect:/user/"+userId+"/store/"+storeId+"/items";
	}
	private void saveNewOrEditedItem(long storeId, InventoryItem item) {
		Store store = storeService.findById(storeId);
		item.setDefaultColor();
		item.setStore(store);
		store.addItem(item);
		itemService.save(item);
		store.setDefaultColor();
		storeService.save(store);
	}
}
