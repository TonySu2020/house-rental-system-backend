package com.example.houserentalsystembackend.repository;

import com.example.houserentalsystembackend.model.entity.Agent;
import java.math.BigInteger;
import java.util.Map;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface AnalysisRepository extends CrudRepository<Agent, String> {

  @Query(value = "call get_overview()", nativeQuery = true)
  Map<String, BigInteger> GET_OVERVIEW();
}
