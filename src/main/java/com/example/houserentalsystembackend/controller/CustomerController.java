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
      return BaseResponse.ok(customerList, "Found Customers");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }

  @PostMapping(value = "/api/customers")
  public BaseResponse<Customer> addCustomer(@RequestBody Customer customer) {
    try {
      if (customerService.findById(customer.getId()) != null) {
        return BaseResponse.error(409, "This customer has been registered.");
      }
      Customer newCustomer = customerService.addCustomer(customer);
      return BaseResponse.ok(newCustomer, "Customer Added");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }

  @GetMapping(value = "/api/customers/{id}")
  public BaseResponse<Customer> findById(@PathVariable("id") String id) {
    try {
      Customer customer = customerService.findById(id);
      if (customer == null) {
        return BaseResponse.error(404, "No Such Customer");
      }
      return BaseResponse.ok(customer, "Found Customer");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }

  @DeleteMapping(value = "/api/customers/{id}")
  public BaseResponse<Customer> deleteById(@PathVariable("id") String id) {
    try {
      Customer customer = customerService.findById(id);
      if (customer == null) {
        return BaseResponse.error(404, "No Such Customer");
      }
      if (!customerService.isSafeToDeleteCustomer(id)) {
        return BaseResponse.error(403,
          "Delete Failed! This customer has leases! If you want to delete it anyway, use HARD DELETE instead.");
      }
      customerService.deleteCustomer(id);
      return BaseResponse.ok(null, "Customer Deleted");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }

  @PutMapping(value = "/api/customers/{id}")
  public BaseResponse<Customer> updateById(@PathVariable("id") String id,
    @RequestBody Customer customer) {
    try {
      Customer oldCustomer = customerService.findById(id);
      if (oldCustomer == null) {
        return BaseResponse.error(404, "No Such Customer");
      }
      Customer newCustomer;
      oldCustomer.setId(customer.getId());
      oldCustomer.setEmail(customer.getEmail());
      oldCustomer.setFirstName(customer.getFirstName());
      oldCustomer.setLastName(customer.getLastName());
      oldCustomer.setPhone(customer.getPhone());

      newCustomer = customerService.updateCustomer(oldCustomer);

      return BaseResponse.ok(newCustomer, "Customer Updated");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }

  @GetMapping(value = "/api/customers/email/{email}")
  public BaseResponse<List<Customer>> findByEmail(@PathVariable("email") String email) {
    try {
      List<Customer> customerList = customerService.findByEmail(email);
      return BaseResponse.ok(customerList, "Found Customers");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }


  @GetMapping(value = "/api/customers/phone/{phone}")
  public BaseResponse<List<Customer>> findByPhone(@PathVariable("phone") String phone) {
    try {
      List<Customer> customerList = customerService.findByPhone(phone);
      return BaseResponse.ok(customerList, "Found Customers");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }

  @DeleteMapping(value = "/api/customers/{id}/hard")
  public BaseResponse<Customer> hardDeleteById(@PathVariable("id") String id) {
    try {
      Customer customer = customerService.findById(id);
      if (customer == null) {
        return BaseResponse.error(404, "No Such Customer");
      }
      customerService.hardDeleteCustomer(id);
      return BaseResponse.ok(null, "Customer Deleted");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }
}
