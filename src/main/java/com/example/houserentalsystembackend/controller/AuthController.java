package com.example.houserentalsystembackend.controller;

import com.example.houserentalsystembackend.model.BaseResponse;
import com.example.houserentalsystembackend.model.LoginForm;
import com.example.houserentalsystembackend.model.entity.Agent;
import com.example.houserentalsystembackend.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
        return BaseResponse.ok(agent, "Login Success");
      }
      return BaseResponse.error(409, "Invalid username or password");
    } catch (Exception e) {
      return BaseResponse.error(null, e.getMessage());
    }
  }

  @GetMapping("/login")
  public BaseResponse<String> login() {
    return BaseResponse.error("Login required!");
  }

}
