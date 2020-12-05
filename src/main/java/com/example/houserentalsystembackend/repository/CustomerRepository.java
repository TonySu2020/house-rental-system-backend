package com.example.houserentalsystembackend.repository;

import com.example.houserentalsystembackend.model.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {

}
