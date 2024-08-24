package com.india.railway.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TableBasedIdGeneratorService {

    // @Autowired
    // private EntityManager entityManager;

    @Transactional
    public Long generateNextId(String sequenceName, int incrementSize) {
        // Fetch the current value from the id_generator table

        /*
         * Query selectQuery = entityManager.createNativeQuery(
         * "SELECT current_value FROM id_generator WHERE sequence_name = :sequenceName FOR UPDATE"
         * );
         * selectQuery.setParameter("sequenceName", sequenceName);
         * Long currentValue = ((Number) selectQuery.getSingleResult()).longValue();
         * 
         * // Increment the current value
         * Long nextValue = currentValue + incrementSize;
         * 
         * // Update the current value in the table
         * Query updateQuery = entityManager.createNativeQuery(
         * "UPDATE id_generator SET current_value = :nextValue WHERE sequence_name = :sequenceName"
         * );
         * updateQuery.setParameter("nextValue", nextValue);
         * updateQuery.setParameter("sequenceName", sequenceName);
         * updateQuery.executeUpdate();
         */

        return (long) 4590;
    }
}