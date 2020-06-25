package com.example.InventoryTracker.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.InventoryTracker.Domain.Store;
import com.example.InventoryTracker.Domain.User;
import com.example.InventoryTracker.Service.StoreService;
import com.example.InventoryTracker.Service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	StoreService storeService;
//	@Autowired
//	InventoryItemRepository itemRepo;
	
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
		model.put("count", userStores.size());
//		if(userStores.isEmpty()) {
//			model.put("count", 0);
//		}else {
//			model.put("count", userStores.size());			
//		}
		return "home";
	}
	@ResponseBody
	@PostMapping("/user/{userId}/addStore")
	public String store(@RequestBody String storeName, @PathVariable long userId){
		User user = userService.findById(userId);
		Store store = new Store();
		store.setUser(user);
		store.setStoreName(storeName);
		user.addStore(store);
		userService.save(user);
		storeService.save(store);
		return "store";
	}	
}
