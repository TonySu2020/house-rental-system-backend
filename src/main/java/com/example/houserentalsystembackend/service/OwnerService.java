package com.example.houserentalsystembackend.service;

import com.example.houserentalsystembackend.model.entity.Owner;
import com.example.houserentalsystembackend.repository.OwnerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

  public Owner findById(String id) {
    Optional<Owner> owner = ownerRepository.findById(id);
    return owner.orElse(null);
  }

  public Owner addOwner(Owner owner) {
    return ownerRepository.save(owner);
  }

  public void deleteOwner(String id) {
    ownerRepository.deleteById(id);
  }

  public Owner updateOwner(Owner owner) {
    return ownerRepository.save(owner);
  }

  public List<Owner> findByEmail(String email) {
    return ownerRepository.findAllByEmail(email);
  }

  public List<Owner> findByPhone(String phone) {
    return ownerRepository.findAllByPhone(phone);
  }

  public void hardDeleteOwner(String id) {
    ownerRepository.hardDeleteOwner(id);
  }

  public boolean isSafeToDeleteOwner(String id) {
    return ownerRepository.isSafeToDeleteOwner(id);
  }
}
