package com.india.railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.india.railway.model.User;
import com.india.railway.model.UserProfile;
import com.india.railway.service.UserService;

import reactor.core.publisher.Flux;

@RequestMapping(path = "/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping(path = "/adduser")
	public @ResponseBody String addUsers(@RequestParam String userName, @RequestParam String number,
			@RequestParam String email, @RequestParam String password) {

		User user = new User();
		user.setUsername(userName);

		user.setMobileno(number);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));

		UserProfile up = new UserProfile();
		up.setEmail("user@gmail.com");
		up.setLastName("gandham");
		user.setUserProfile(up);

		userService.saveUser(user);
		return "Details got Saved";
	}

	@GetMapping(path = "/users")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the Book
		return userService.getAllUsers();
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestParam("email") String email) {
		userService.processForgotPassword(email);
		return ResponseEntity.ok("Password reset link sent to email.");
	}

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestParam("token") String token,
			@RequestParam("newPassword") String newPassword) {
		System.out.println();
		userService.resetPassword(token, newPassword);
		return ResponseEntity.ok("Password has been reset.");
	}

	@GetMapping(path = "/flux")
	public Flux<String> getFlux() {
		return Flux.just("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9",
				"Item 10");
	}
}
