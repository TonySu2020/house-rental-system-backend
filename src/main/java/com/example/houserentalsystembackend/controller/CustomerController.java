package com.example.houserentalsystembackend.controller;

import com.example.houserentalsystembackend.model.BaseResponse;
import com.example.houserentalsystembackend.model.entity.Customer;
import com.example.houserentalsystembackend.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @GetMapping(value = "/api/customers")
  public BaseResponse<List<Customer>> findAllCustomer() {
    try {
      List<Customer> customerList = customerService.findAllCustomer();
      return new BaseResponse<>(200, customerList, "Found Customers");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }
}
