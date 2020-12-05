package com.example.houserentalsystembackend.repository;

import com.example.houserentalsystembackend.model.entity.Owner;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OwnerRepository extends CrudRepository<Owner, String> {

//  @Query(value = "SELECT * FROM Owner", nativeQuery = true)
//  List<Owner> findAllWithProcedure();

  @Query(value = ":query", nativeQuery = true)
  List<Owner> findAllWithProcedure(@Param("query") String query);
}
