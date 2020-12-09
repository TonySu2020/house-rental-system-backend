package com.example.houserentalsystembackend.repository;

import com.example.houserentalsystembackend.model.entity.House;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface HouseRepository extends CrudRepository<House, Integer> {

  @Query(value = "call hard_delete_house(:id)", nativeQuery = true)
  boolean hardDeleteHouse(@Param("id") int id);

  @Query(value = "SELECT is_safe_delete_house(:id)", nativeQuery = true)
  boolean isSafeToDeleteHouse(@Param("id") int id);

  @Query(value = "SELECT house FROM House house where house.owner.id = :id")
  List<House> findAllByOwner(@Param("id") String id);

  @Query(value = "SELECT house FROM House house where house.city.zipCode = :zip and "
      + "house.bedroomNumber = :bed and house.bathroomNumber = :bath and "
      + "house.rent >= :min and house.rent <= :max and "
      + "(house.electricityInclude = true or house.electricityInclude = :electric) and "
      + "(house.waterInclude = true or house.waterInclude = :water) and "
      + "(house.gasInclude = true or house.gasInclude = :gas) and "
      + "(house.networkInclude = true or house.networkInclude = :net) and "
      + "(house.nearToTransit = true or house.nearToTransit = :transit) ")
  List<House> findAllByCondition(@Param("zip") String zip, @Param("bed") int bed,
      @Param("bath") int bath, @Param("min") double min, @Param("max") double max,
      @Param("electric") boolean electric, @Param("water") boolean water, @Param("gas") boolean gas,
      @Param("net") boolean net, @Param("transit") boolean transit);
}
