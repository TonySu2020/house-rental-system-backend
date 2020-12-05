package com.example.houserentalsystembackend.service;

import com.example.houserentalsystembackend.model.entity.House;
import com.example.houserentalsystembackend.repository.HouseOwnerRepository;
import com.example.houserentalsystembackend.repository.HouseRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseService {

  @Autowired
  private HouseOwnerRepository houseOwnerRepository;

  @Autowired
  private HouseRepository houseRepository;

  public List<House> findAllHouse() {
    Iterable<House> iterable = houseRepository.findAll();
    List<House> houseList = new ArrayList<>();
    iterable.forEach(houseList::add);
    return houseList;
  }

}
