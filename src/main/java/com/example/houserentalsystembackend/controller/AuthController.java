package com.example.houserentalsystembackend.controller;

import com.example.houserentalsystembackend.model.BaseResponse;
import com.example.houserentalsystembackend.model.LoginForm;
import com.example.houserentalsystembackend.model.entity.Agent;
import com.example.houserentalsystembackend.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
public class AuthController {

  @Autowired
  private AgentService agentService;

  @PostMapping(value = "/api/login")
  public BaseResponse<Agent> verifyLogin(@RequestBody LoginForm loginForm) {
    try {
      Agent agent = agentService.verifyLogin(loginForm.getUsername(), loginForm.getPassword());
      if (agent != null) {
        agent.setPassword(null);
        return new BaseResponse<>(200, agent, "Login Success");
      }
      return new BaseResponse<>(409, null, "Invalid username or password");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

}
