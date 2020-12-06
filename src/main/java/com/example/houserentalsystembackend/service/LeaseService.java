package com.example.houserentalsystembackend.service;

import com.example.houserentalsystembackend.model.entity.Lease;
import com.example.houserentalsystembackend.repository.LeaseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

  public Lease findById(int id) {
    Optional<Lease> lease = leaseRepository.findById(id);
    return lease.orElse(null);
  }

  public Lease addLease(Lease lease) {
    return leaseRepository.save(lease);
  }

  public void deleteLease(int id) {
    leaseRepository.deleteById(id);
  }

  public Lease updateLease(Lease lease) {
    return leaseRepository.save(lease);
  }


}
