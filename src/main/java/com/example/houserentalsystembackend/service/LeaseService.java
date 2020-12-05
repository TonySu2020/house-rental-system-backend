package com.example.houserentalsystembackend.service;

import com.example.houserentalsystembackend.model.entity.Lease;
import com.example.houserentalsystembackend.repository.LeaseRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaseService {

  @Autowired
  private LeaseRepository leaseRepository;

  public List<Lease> findAllLease() {
    Iterable<Lease> iterable = leaseRepository.findAll();
    List<Lease> leaseList = new ArrayList<>();
    iterable.forEach(leaseList::add);
    return leaseList;
  }
}
