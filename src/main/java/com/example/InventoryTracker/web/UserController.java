package com.example.InventoryTracker.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/user/{userId}/home")
	public String userHome(@PathVariable long userId, User user){
		return "home";
	}
	@PostMapping("/user/home")
	public String usertestHome(User user){
		user = userService.checkLoginInputs(user);
		if(user==null) return "redirect:/user/signup";
		return "redirect:/user/"+user.getId()+"/home";
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
	@PostMapping("/user/signup")
	public Long verifyAndSignup(@RequestBody User user){
		 User userWithEmail=userService.checkEmail(user);
		 User userWithName=userService.checkUsername(user);
		 if(userWithEmail!= null) return null;
		 if(userWithName!= null) return null;
		userService.save(user);
		return user.getId();
	}
	@ResponseBody
	@PostMapping("/user/login")
	public Boolean verifyAndLogin(@RequestBody User user){
		User foundUser=userService.checkLoginInputs(user);
		if(!(foundUser==null)) return true;
		return false;
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
