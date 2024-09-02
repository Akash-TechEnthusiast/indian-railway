package com.india.railway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.india.railway.model.MenuItem;
import com.india.railway.repository.DashBoardRepository;

@Service
public class DashBoardService {

    @Autowired
    private DashBoardRepository dashBoardRepository;

    public List<MenuItem> getAllMenuItems() {
        return dashBoardRepository.findAll();
    }

}