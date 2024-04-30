package com.india.railway.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.india.railway.model.User;
import com.india.railway.repository.UserRepository; 


@RequestMapping(path="/user") 
@RestController	
public class UserController { 
	
	@Autowired
	private UserRepository userRepository; 

	@PostMapping(path="/adduser") 
	public @ResponseBody String addUsers (@RequestParam String userName 
			, @RequestParam String number,@RequestParam String email 
			, @RequestParam String password) { 
	
		User user = new User(); 
		user.setUsername(userName);
		
	    user.setMobileno(number);
		user.setEmail(email);
		user.setPassword(password);
		
		userRepository.save(user); 
		return "Details got Saved"; 
	} 

	@GetMapping(path="/users") 
	public @ResponseBody Iterable<User> getAllUsers() { 
		// This returns a JSON or XML with the Book 
		return userRepository.findAll(); 
	} 
} 
