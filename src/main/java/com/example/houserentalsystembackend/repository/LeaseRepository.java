package com.example.houserentalsystembackend.repository;

import com.example.houserentalsystembackend.model.entity.Lease;
import org.springframework.data.repository.CrudRepository;

public interface LeaseRepository extends CrudRepository<Lease, Integer> {

}
