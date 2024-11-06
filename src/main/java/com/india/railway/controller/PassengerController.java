package com.india.railway.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.india.railway.model.Passenger;
import com.india.railway.service.PassengerService;

import lombok.extern.slf4j.Slf4j;

import jakarta.validation.Valid;

@RestController
@Slf4j
@Validated
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    // Add new Customer
    @PostMapping("/addPassenger")
    public ResponseEntity<String> addPassenger(@Valid @RequestBody Passenger passenger) throws IllegalAccessException {

        log.info("Info level================>>" + passenger.toString());
        log.error("Error level");
        return ResponseEntity.ok(passengerService.addPassenger(passenger));
    }

    // Get Customer by Id
    @GetMapping("/getPassenger/{id}")
    public Optional<Passenger> getPassenger(@PathVariable("id") Long id) {
        return passengerService.getPassenger(id);
    }

    // Update Customer details
    @PutMapping("/updatePassenger")
    public String updatePassenger(@RequestBody Passenger passenger) {
        return passengerService.updatePassenger(passenger);
    }

    @GetMapping("/getAllPassengers")
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

}