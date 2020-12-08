package com.example.houserentalsystembackend.repository;

import com.example.houserentalsystembackend.model.entity.Owner;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, String> {

  @Query
  List<Owner> findAllByEmail(String email);

  @Query
  List<Owner> findAllByPhone(String phone);
}
