package com.india.railway.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.india.railway.exception.NoSuchEmployeeExistsException;
import com.india.railway.model.Passenger;
import com.india.railway.repository.PassengerRepository;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public Optional<Passenger> getPassenger(Long id) {
        // TODO Auto-generated method stub
        return passengerRepository.findById(id);

        // throw new UnsupportedOperationException("Unimplemented method
        // 'getPassenger'");
    }

    @Override
    public String addPassenger(Passenger passenger) {
        passengerRepository.save(passenger);
        return "";
        // throw new UnsupportedOperationException("Unimplemented method
        // 'addPassenger'");
    }

    @Override
    public String updatePassenger(Passenger passenger) {
        // TODO Auto-generated method stub
        passengerRepository.save(passenger);
        throw new UnsupportedOperationException("Unimplemented method 'updatePassenger'");
    }

    @Override
    public List<Passenger> getAllPassengers() {

        return passengerRepository.findAll();
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'getAllPassengers'");
    }

}