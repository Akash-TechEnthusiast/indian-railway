package com.india.railway.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.india.railway.exception.NoSuchEmployeeExistsException;
import com.india.railway.exception.NoSuchPassengerExistsException;
import com.india.railway.exception.PassengerAlreadyExistsException;

import com.india.railway.model.Passenger;
import com.india.railway.repository.PassengerRepository;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    AutoCodeGeneratorService autoCodeGeneratorService;

    @Override
    public Optional<Passenger> getPassenger(Long id) {
        // TODO Auto-generated method stub

        return Optional.ofNullable(passengerRepository.findById(id)
                .orElseThrow(() -> new NoSuchPassengerExistsException("NO PASSENGER PRESENT WITH ID = " + id)));

        // throw new UnsupportedOperationException("Unimplemented method
        // 'getPassenger'");
    }

    @Override
    public String addPassenger(Passenger passenger) throws IllegalAccessException {
        // passengerRepository.save(passenger);
        // return "";
        // throw new UnsupportedOperationException("Unimplemented method
        // 'addPassenger'");

        Passenger existingPassenger = passengerRepository.findById(passenger.getId()).orElse(null);
        if (existingPassenger == null) {

            // long nextvalueis =
            // autoCodeGeneratorService.generateNextId("passenger_entity", 1);
            // System.out.println(nextvalueis);

            autoCodeGeneratorService.generateId(passenger);

            passengerRepository.save(passenger);
            return "Passenger added successfully";
        } else
            throw new PassengerAlreadyExistsException("Passenger already exists!!");
    }

    @Override
    public String updatePassenger(Passenger passenger) {
        Passenger existingPassenger = passengerRepository.findById(passenger.getId()).orElse(null);
        if (existingPassenger == null)
            throw new NoSuchEmployeeExistsException("No Such Employee exists!!");
        else {
            existingPassenger.setName(passenger.getName());
            existingPassenger.setAddress(passenger.getAddress());
            passengerRepository.save(existingPassenger);
            return "Record updated Successfully";
        }
    }

    @Override
    public List<Passenger> getAllPassengers() {

        return passengerRepository.findAll();
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'getAllPassengers'");
    }

}