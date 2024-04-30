package com.india.railway.repository;




import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.Customer; 

@Repository
public interface CustomerRepo extends CrudRepository<Customer,String> { 

} 
