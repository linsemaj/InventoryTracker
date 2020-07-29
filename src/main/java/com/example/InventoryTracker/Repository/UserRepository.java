package com.example.InventoryTracker.Repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.InventoryTracker.Domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where username= :username and password= :password ")
	Set<User> findUserWithUsernameAndPassword(String username, String password);
	
	@Query("select u from User u where username= :username")
	List<User> findUsername(String username);

	@Query("select u from User u where email= :email")
	List<User> findEmail(String email);
	
	
}
