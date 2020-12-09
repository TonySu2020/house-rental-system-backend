package com.example.houserentalsystembackend.repository;

import com.example.houserentalsystembackend.model.entity.Lease;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LeaseRepository extends CrudRepository<Lease, Integer> {

  @Query(value = "SELECT lease FROM Lease lease WHERE lease.house.owner.id = :id")
  List<Lease> findAllByOwnerId(@Param("id") String id);

  @Query(value = "SELECT lease FROM Lease lease WHERE lease.customer.id = :id")
  List<Lease> findAllByCustomerId(@Param("id") String id);

  @Query(value = "SELECT lease FROM Lease lease WHERE lease.endDate < :date")
  List<Lease> findAllClosed(@Param("date") Date date);

  @Query(value = "SELECT lease FROM Lease lease WHERE lease.endDate > :date and lease.startDate < :date")
  List<Lease> findAllOnGoing(@Param("date") Date date);

  @Query(value = "SELECT lease FROM Lease lease WHERE lease.endDate > :date and lease.endDate < :late")
  List<Lease> findAllClosing(@Param("date") Date date, @Param("late") Date late);


}
