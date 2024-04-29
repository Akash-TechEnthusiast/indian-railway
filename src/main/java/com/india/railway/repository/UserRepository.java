package com.india.railway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Define custom queries or methods if needed
}