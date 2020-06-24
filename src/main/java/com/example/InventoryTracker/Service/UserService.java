package com.example.InventoryTracker.Service;

import java.util.List;
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

	public User checkEmail(User user) {
		List<User> userWithEmail = userRepo.findEmail(user.getEmail());
		if(!userWithEmail.isEmpty()) return userWithEmail.get(0);
		return null;
	}

	public User checkUsername(User user) {
		List<User> usersWithUsername = userRepo.findUsername(user.getUsername());
		if(!usersWithUsername.isEmpty()) return usersWithUsername.get(0);
		return null;
		
	}

	public User checkLoginInputs(User user) {
		Set<User> setFoundUser = userRepo.findUserWithUsernameAndPassword(user.getUsername(), user.getPassword());
		if(!setFoundUser.isEmpty()) return user=setFoundUser.iterator().next();
		return null;
	}

	
	
	
}
