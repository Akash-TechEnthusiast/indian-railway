package com.india.railway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.india.railway.model.College;

public interface CollegeRepository extends JpaRepository<College, Long> {
}
