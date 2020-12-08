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

  public void deleteCustomer(String id) {
    customerRepository.deleteById(id);
  }

  public Customer updateCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  public Customer HardUpdateCustomer(Customer customer) {
    return null;
  }

  public List<Customer> findByEmail(String email) {
    return customerRepository.findAllByEmail(email);
  }

  public List<Customer> findByPhone(String phone) {
    return customerRepository.findAllByPhone(phone);
  }
}
