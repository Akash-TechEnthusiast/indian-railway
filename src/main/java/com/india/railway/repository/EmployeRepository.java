package com.india.railway.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.Employee;

@Repository
public interface EmployeRepository extends JpaRepository<Employee, Long> {
}