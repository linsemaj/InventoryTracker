package com.example.InventoryTracker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemsController {

	@GetMapping("/")
	public String start(){
		
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String home(){
		
		return "home";
	}
	@GetMapping("/signup")
	public String signUp(){
		
		return "signIn";
	}
	@GetMapping("/store")
	public String store(){
		
		return "store";
	}
}
