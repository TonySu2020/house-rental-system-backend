package com.example.houserentalsystembackend.repository;

import com.example.houserentalsystembackend.model.entity.Owner;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OwnerRepository extends CrudRepository<Owner, String> {

  @Query
  List<Owner> findAllByEmail(String email);

  @Query
  List<Owner> findAllByPhone(String phone);

  @Query(value = "call hard_delete_owner(:id)", nativeQuery = true)
  boolean hardDeleteOwner(@Param("id")String id);

  @Query(value ="SELECT is_safe_delete_owner(:id)", nativeQuery = true)
  boolean isSafeToDeleteOwner(@Param("id")String id);
}
