package com.example.houserentalsystembackend.controller;

import com.example.houserentalsystembackend.model.BaseResponse;
import com.example.houserentalsystembackend.model.entity.Customer;
import com.example.houserentalsystembackend.model.entity.Owner;
import com.example.houserentalsystembackend.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping(value = "/api/customers")
  public BaseResponse<Customer> addCustomer(@RequestBody Customer customer) {
    try {
      if (customerService.findById(customer.getId()) == null) {
        return new BaseResponse<>(409, null, "This customer has been registered.");
      }
      Customer newCustomer = customerService.addCustomer(customer);
      return new BaseResponse<>(200, newCustomer, "Customer Added");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }
}
