package com.example.houserentalsystembackend.repository;

import com.example.houserentalsystembackend.model.entity.House;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface HouseRepository extends CrudRepository<House, Integer> {

  @Query(value = "call hard_delete_house(:id)", nativeQuery = true)
  boolean hardDeleteHouse(@Param("id")int id);

  @Query(value ="SELECT is_safe_delete_house(:id)", nativeQuery = true)
  boolean isSafeToDeleteHouse(@Param("id")int id);

  @Query(value = "SELECT house FROM House house where house.owner.id = :id")
  List<House> findAllByOwner(@Param("id")String id);
}
