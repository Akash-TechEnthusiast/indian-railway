package com.india.railway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PrePersist;
import javax.persistence.Query;
import javax.transaction.Transactional;

import java.lang.reflect.Field;

@Component
public class CustomIdGenerationListener {

    // @Autowired
    // private TableBasedIdGeneratorService idGeneratorService;

    // @PersistenceContext

    @Autowired
    private EntityManager entityManager;

    @PrePersist
    public void generateId(Object entity) throws IllegalAccessException {
        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(CustomGeneratedValue.class)) {
                CustomGeneratedValue customGeneratedValue = field.getAnnotation(CustomGeneratedValue.class);

                String entityName = customGeneratedValue.sequenceName();
                int idvalue = customGeneratedValue.incrementSize();
                // Generate the next ID using the TableBasedIdGeneratorService
                Long nextId = generateNextId(entityName,
                        idvalue);

                // Set the generated ID to the field
                field.setAccessible(true);
                field.set(entity, nextId);
            }
        }
    }

    @Transactional
    public Long generateNextId(String sequenceName, int incrementSize) {
        // Fetch the current value from the id_generator table
        Query selectQuery = entityManager.createNativeQuery(
                "SELECT current_value FROM id_generator WHERE sequence_name = :sequenceName FOR UPDATE");
        selectQuery.setParameter("sequenceName", sequenceName);
        Long currentValue = ((Number) selectQuery.getSingleResult()).longValue();

        // Increment the current value
        Long nextValue = currentValue + incrementSize;

        // Update the current value in the table
        Query updateQuery = entityManager.createNativeQuery(
                "UPDATE id_generator SET current_value = :nextValue WHERE sequence_name = :sequenceName");
        updateQuery.setParameter("nextValue", nextValue);
        updateQuery.setParameter("sequenceName", sequenceName);
        updateQuery.executeUpdate();

        return nextValue;
    }

}