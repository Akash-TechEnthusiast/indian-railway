package com.india.railway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}