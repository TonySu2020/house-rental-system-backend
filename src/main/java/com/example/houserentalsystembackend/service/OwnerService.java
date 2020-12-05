package com.example.houserentalsystembackend.service;

import com.example.houserentalsystembackend.model.entity.Owner;
import com.example.houserentalsystembackend.repository.OwnerRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

  @Autowired
  private OwnerRepository ownerRepository;

  public List<Owner> findAllOwner() {
    Iterable<Owner> iterable = ownerRepository.findAll();
    List<Owner> ownerList = new ArrayList<>();
    iterable.forEach(ownerList::add);
    return ownerList;
  }
}
