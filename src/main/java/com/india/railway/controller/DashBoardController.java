package com.india.railway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.india.railway.model.Employee;
import com.india.railway.model.MenuItem;
import com.india.railway.service.DashBoardService;
import com.india.railway.service.EmployeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DashBoardController {

    @Autowired
    private DashBoardService dashBoardService;

    // Get Customer by Id
    @GetMapping("/getDashboard")
    public List<MenuItem> getDashBoardItems() {
        return dashBoardService.getAllMenuItems();
    }

}