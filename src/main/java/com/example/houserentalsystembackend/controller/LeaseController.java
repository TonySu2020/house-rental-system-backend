package com.example.houserentalsystembackend.controller;

import com.example.houserentalsystembackend.model.BaseResponse;
import com.example.houserentalsystembackend.model.entity.Lease;
import com.example.houserentalsystembackend.service.LeaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
public class LeaseController {

  @Autowired
  private LeaseService leaseService;

  @GetMapping(value = "/api/leases")
  public BaseResponse<List<Lease>> findAllLease() {
    try {
      List<Lease> leaseList = leaseService.findAllLease();
      return new BaseResponse<>(200, leaseList, "Found Leases");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }
}
