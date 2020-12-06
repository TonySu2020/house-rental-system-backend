package com.example.houserentalsystembackend.controller;

import com.example.houserentalsystembackend.model.BaseResponse;
import com.example.houserentalsystembackend.model.entity.Agent;
import com.example.houserentalsystembackend.model.entity.City;
import com.example.houserentalsystembackend.model.entity.Customer;
import com.example.houserentalsystembackend.model.entity.House;
import com.example.houserentalsystembackend.model.entity.Lease;
import com.example.houserentalsystembackend.model.entity.Owner;
import com.example.houserentalsystembackend.service.AgentService;
import com.example.houserentalsystembackend.service.CustomerService;
import com.example.houserentalsystembackend.service.HouseService;
import com.example.houserentalsystembackend.service.LeaseService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
public class LeaseController {

  @Autowired
  private LeaseService leaseService;

  @Autowired
  private HouseService houseService;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private AgentService agentService;

  @GetMapping(value = "/api/leases")
  public BaseResponse<List<Lease>> findAllLease() {
    try {
      List<Lease> leaseList = leaseService.findAllLease();
      return new BaseResponse<>(200, leaseList, "Found Leases");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

  @PostMapping(value = "/api/leases")
  public BaseResponse<Lease> addLease(@RequestBody Lease lease) {
    try {
      Agent agent = agentService.findById(lease.getAgent().getId());
      if(agent == null) {
        return new BaseResponse<>(404, null, "No Such Agent");
      }
      lease.setAgent(agent);

      House house = houseService.findById(lease.getHouse().getId());
      if (house == null) {
        return new BaseResponse<>(404, null, "No Such House");
      }
      lease.setHouse(house);

      Customer customer = customerService.findById(lease.getCustomer().getId());
      if(customer == null) {
        return new BaseResponse<>(404, null, "No Such Customer");
      }
      lease.setCustomer(customer);
//      lease.setStartDate(new Date());
//      lease.setEndDate(new Date());
      Lease newLease = leaseService.addLease(lease);

      return new BaseResponse<>(200, newLease, "Lease Added");

    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

  @GetMapping(value = "/api/leases/{id}")
  public BaseResponse<Lease> findById(@PathVariable("id") int id) {
    try {
      Lease lease = leaseService.findById(id);
      if (lease == null) {
        return new BaseResponse<>(404, null, "No Such Lease");
      }
      return new BaseResponse<>(200, lease, "Found Lease");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

  @DeleteMapping(value = "/api/leases/{id}")
  public BaseResponse<Lease> deleteById(@PathVariable("id") int id) {
    try {
      if(leaseService.findById(id) == null) {
        return new BaseResponse<>(404, null, "No Such Lease");
      }
      leaseService.deleteLease(id);
      return new BaseResponse<>(200, null, "Lease Deleted");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

  @PutMapping(value = "/api/leases/{id}")
  public BaseResponse<Lease> updateById(@PathVariable("id") int id, @RequestBody Lease lease) {
    try {
      Lease oldLease = leaseService.findById(id);
      if (oldLease == null) {
        return new BaseResponse<>(404, null, "No Such Lease");
      }

      Agent agent = agentService.findById(lease.getAgent().getId());
      if(agent == null) {
        return new BaseResponse<>(404, null, "No Such Agent");
      }
      oldLease.setAgent(agent);

      House house = houseService.findById(lease.getHouse().getId());
      if (house == null) {
        return new BaseResponse<>(404, null, "No Such House");
      }
      oldLease.setHouse(house);

      Customer customer = customerService.findById(lease.getCustomer().getId());
      if(customer == null) {
        return new BaseResponse<>(404, null, "No Such Customer");
      }
      oldLease.setCustomer(customer);

      oldLease.setActualRent(lease.getActualRent());
      oldLease.setStartDate(lease.getStartDate());
      oldLease.setEndDate(lease.getEndDate());

      Lease newLease = leaseService.updateLease(oldLease);

      return new BaseResponse<>(200, newLease, "Lease Updated");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }


}
