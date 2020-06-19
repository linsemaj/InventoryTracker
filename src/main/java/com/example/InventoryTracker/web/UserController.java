package com.example.InventoryTracker.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@GetMapping("/user/home")
	public String userHome(){
		
		return "home";
	}
	@GetMapping("/user/signIn")
	public String signUp(ModelMap model){
		model.put("user", new User());
		return "signIn";
	}
	@GetMapping("/user/store")
	public String store(){
		
		return "store";
	}
	@ResponseBody
	@GetMapping("/checkDuplicationUsernameAndEmail")
	public User verifyUser(@RequestBody User user){
		userService.checkEmail(user.getEmail());
		userService.checkUsername(user.getUsername());
		return null;
	}
	@PostMapping("/signup")
	public String signedUp(User user){
		userService.addUser(user);
		return "redirect:/user/home";
	}
	@PostMapping("/login")
	public String login(User user){
		user =userService.verifyUser(user);
		if(!user.equals(null)) {			
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
			System.out.println(user.getEmail());
		}
		return "redirect:/user/home";
	}
	
}
