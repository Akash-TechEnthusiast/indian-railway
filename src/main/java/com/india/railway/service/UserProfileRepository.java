package com.india.railway.service;


import org.springframework.data.jpa.repository.JpaRepository;

import com.india.railway.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}