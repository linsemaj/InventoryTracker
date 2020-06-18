package com.example.InventoryTracker.Service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.InventoryTracker.Domain.User;
import com.example.InventoryTracker.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	public User save(User user) {
		return userRepo.save(user);
	}

	public User verifyUser(User user) {
		// TODO Auto-generated method stub
		 Set<User> foundUser=userRepo.findUserWithUsernameAndPassword(user.getUsername(),user.getPassword());
		 if(foundUser.iterator().hasNext()) {
			 user = foundUser.iterator().next(); 
			 return user;
		 }
		 return null;
	}

	public User addUser(User user) {

		return userRepo.save(user);
	}
	
	
	
}
