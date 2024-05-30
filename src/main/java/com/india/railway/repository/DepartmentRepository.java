package com.india.railway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.india.railway.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
}