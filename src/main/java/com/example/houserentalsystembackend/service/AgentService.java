package com.example.houserentalsystembackend.service;

import com.example.houserentalsystembackend.model.entity.Agent;
import com.example.houserentalsystembackend.repository.AgentRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

  @Autowired
  private AgentRepository agentRepository;

  public Agent verifyLogin(String username, String password) {
    Optional<Agent> agent = agentRepository.findAgentByUsernameAndPassword(username, password);
    return agent.orElse(null);
  }

  public Agent findById(int id) {
    Optional<Agent> agent = agentRepository.findById(id);
    return agent.orElse(null);
  }

}
