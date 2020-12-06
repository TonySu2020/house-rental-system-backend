package com.example.houserentalsystembackend.service;


import com.example.houserentalsystembackend.model.entity.Customer;
import com.example.houserentalsystembackend.model.entity.Owner;
import com.example.houserentalsystembackend.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  public List<Customer> findAllCustomer() {
    List<Customer> customerList = new ArrayList<>();
    Iterable<Customer> iterable = customerRepository.findAll();
    iterable.forEach(customerList::add);
    return customerList;
  }

  public Customer findById(String id) {
    Optional<Customer> customer = customerRepository.findById(id);
    return customer.orElse(null);
  }

  public Customer addCustomer(Customer customer) {
    return customerRepository.save(customer);
  }
}
