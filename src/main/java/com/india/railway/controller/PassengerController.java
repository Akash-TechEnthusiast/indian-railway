package com.india.railway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.india.railway.model.Passenger;
import com.india.railway.service.EmployeService;
import com.india.railway.service.PassengerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j

public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    // Add new Customer
    @PostMapping("/addPassenger")
    public String addEmployee(@RequestBody Passenger passenger) {

        log.info("Info level================>>" + passenger.toString());
        log.error("Error level");
        return passengerService.addPassenger(passenger);
    }

}