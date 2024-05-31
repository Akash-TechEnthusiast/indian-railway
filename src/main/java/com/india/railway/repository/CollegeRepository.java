package com.india.railway.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.india.railway.model.College;

public interface CollegeRepository extends PagingAndSortingRepository<College, Long> {
	
	 Page<College> findByName(String name, Pageable pageable);
}
