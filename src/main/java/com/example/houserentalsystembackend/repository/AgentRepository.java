package com.example.houserentalsystembackend.repository;

import com.example.houserentalsystembackend.model.entity.Agent;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AgentRepository extends CrudRepository<Agent, Integer> {

  @Query
  Optional<Agent> findAgentByUsernameAndPassword(String username, String password);
}
