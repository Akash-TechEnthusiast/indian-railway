package com.india.railway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.india.railway.model.Employee;
import com.india.railway.service.EmployeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EmployeeController {
 
	@Autowired
	private EmployeService employeeService;
	


	// Get Customer by Id
	@GetMapping("/getEmployee/{id}")
	public Employee getEmployee(@PathVariable("id") Long id) {
		return employeeService.getEmployee(id);
	}

	// Add new Customer
	@PostMapping("/addEmployee")
	public String addEmployee(@RequestBody Employee employee) {
		
		
		log.info("Info level================>>"+employee.toString());
		    log.error("Error level"); 
		return employeeService.addEmployee(employee);
	}

	// Update Customer details
	@PutMapping("/updateEmployee")
	public String updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}
	
	@GetMapping("/getAllEmployee")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}
	
	
}