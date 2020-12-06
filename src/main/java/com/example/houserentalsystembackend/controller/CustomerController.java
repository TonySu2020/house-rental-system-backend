package com.example.houserentalsystembackend.controller;

import com.example.houserentalsystembackend.model.BaseResponse;
import com.example.houserentalsystembackend.model.entity.Customer;
import com.example.houserentalsystembackend.service.CustomerService;
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

  @GetMapping(value = "/api/customers/{id}")
  public BaseResponse<Customer> findById(@PathVariable("id") String id) {
    try {
      Customer customer = customerService.findById(id);
      if (customer == null) {
        return new BaseResponse<>(404, null, "No Such Customer");
      }
      return new BaseResponse<>(200, customer, "Found Customer");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

  @DeleteMapping(value = "/api/customers/{id}")
  public BaseResponse<Customer> deleteById(@PathVariable("id") String id) {
    try {
      customerService.deleteCustomer(id);
      return new BaseResponse<>(200, null, "Customer Deleted");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

  @PutMapping(value = "/api/customers/{id}")
  public BaseResponse<Customer> updateById(@PathVariable("id") String id, @RequestBody Customer customer) {
    try {
      Customer oldCustomer = customerService.findById(id);
      if (oldCustomer == null) {
        return new BaseResponse<>(404, null, "No Such Customer");
      }
      Customer newCustomer;
      oldCustomer.setId(customer.getId());
      oldCustomer.setEmail(customer.getEmail());
      oldCustomer.setFirstName(customer.getFirstName());
      oldCustomer.setLastName(customer.getLastName());
      oldCustomer.setPhone(customer.getPhone());
      if(customer.getId().equals(id)) {
        newCustomer = customerService.updateCustomer(oldCustomer);
      } else {
        newCustomer = customerService.HardUpdateCustomer(oldCustomer);
      }
      return new BaseResponse<>(200, newCustomer, "Customer Updated");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }
}
