package com.india.railway.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.india.railway.exception.EmployeeAlreadyExistsException;
import com.india.railway.exception.NoSuchEmployeeExistsException;
import com.india.railway.model.Employee;
import com.india.railway.repository.EmployeRepository;
 
@Service
public class EmployeeServiceImpl
    implements EmployeService {
 
    @Autowired
    private EmployeRepository empRespository;
 
    // Method to get customer by Id.Throws
    // NoSuchElementException for invalid Id
	public Employee getEmployee(Long id) {
		return empRespository.findById(id)
				.orElseThrow(() -> new NoSuchEmployeeExistsException("NO EMPLOYEE PRESENT WITH ID = " + id));
	}
 
    // Method to add new customer details to database.Throws
    // CustomerAlreadyExistsException when customer detail
    // already exist
	public String addEmployee(Employee employee) {
		Employee existingEmployee = empRespository.findById( employee.getId()).orElse(null);
		if (existingEmployee == null) {
			empRespository.save(employee);
			return "Employee added successfully";
		} else
			throw new EmployeeAlreadyExistsException("Employee already exists!!");
	}

    // Method to update customer details to database.Throws
    // NoSuchCustomerExistsException when customer doesn't
    // already exist in database
	public String updateEmployee(Employee employee) {
		Employee existingEmployee = empRespository.findById(employee.getId()).orElse(null);
		if (existingEmployee == null)
			throw new NoSuchEmployeeExistsException("No Such Employee exists!!");
		else {
			existingEmployee.setName(employee.getName());
			existingEmployee.setAddress(employee.getAddress());
			empRespository.save(existingEmployee);
			return "Record updated Successfully";
		}
	}

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return empRespository.findAll();
	}

	

}