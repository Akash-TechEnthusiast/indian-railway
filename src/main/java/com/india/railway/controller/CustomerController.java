package com.india.railway.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.india.railway.model.Customer;
import com.india.railway.service.CustomerService; 

@RestController
@RequestMapping("/customer") 
public class CustomerController { 

	// Inject the CustomerService dependency into class 
	@Autowired
	private CustomerService service; 

	// to insert new customer data into the Redis database. 
	@PostMapping
	public Customer addCustomer(@RequestBody Customer customer){ 

		return service.addCustomer(customer); 
	} 

	// to fetch All the customers from the Redis database 
	@GetMapping
	public List<Customer> getListOfCustomers(){ 
	
		return service.getAllCustomers(); 
	} 

	// to fetch Customer data using ID from Redis Database 
	@GetMapping("/{id}") 
	public Customer getCustomer(@PathVariable int id){ 
		
		return service.getCustomerById(id); 
	} 

	// to update an existing customer in the Redis database using ID. 
	@PutMapping("/{id}") 
	public Customer 
	updateCustomer(@PathVariable int id, 
				@RequestBody Customer newCustomer){ 
		
		return service.updateCustomerById(id, newCustomer); 
	} 

	// to delete an existing customer from the Redis database using ID 
	@DeleteMapping("/{id}") 
	public String deleteCustomer(@PathVariable int id){ 
		
		service.deleteCustomerById(id); 
		return "Customer Deleted Successfully"; 
	} 
}
