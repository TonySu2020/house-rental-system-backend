package com.example.houserentalsystembackend.repository;

import com.example.houserentalsystembackend.model.entity.Customer;
import com.example.houserentalsystembackend.model.entity.Owner;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends CrudRepository<Customer, String> {

  @Query
  List<Customer> findAllByEmail(String email);

  @Query
  List<Customer> findAllByPhone(String phone);

  @Query(value = "call hard_delete_customer(:id)", nativeQuery = true)
  boolean hardDeleteCustomer(@Param("id")String id);

  @Query(value ="SELECT is_safe_delete_customer(:id)", nativeQuery = true)
  boolean isSafeToDeleteCustomer(@Param("id")String id);
}
