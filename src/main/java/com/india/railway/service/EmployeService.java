package com.india.railway.service;

import java.util.List;

import com.india.railway.model.Employee;

public interface EmployeService {

    // Method to get customer by its Id
    Employee getEmployee(Long id);

    // Method to add a new Customer
    // into the database
    String addEmployee(Employee customer);

    // Method to update details of a Customer
    String updateEmployee(Employee customer);

    List<Employee> getAllEmployee();

}