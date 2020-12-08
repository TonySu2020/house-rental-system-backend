package com.example.houserentalsystembackend.repository;

import com.example.houserentalsystembackend.model.entity.Customer;
import com.example.houserentalsystembackend.model.entity.Owner;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {

  @Query
  List<Customer> findAllByEmail(String email);

  @Query
  List<Customer> findAllByPhone(String phone);
}
