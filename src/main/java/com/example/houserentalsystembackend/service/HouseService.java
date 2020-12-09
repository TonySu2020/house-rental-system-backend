package com.example.houserentalsystembackend.service;

import com.example.houserentalsystembackend.model.entity.House;
import com.example.houserentalsystembackend.repository.HouseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseService {

  @Autowired
  private HouseRepository houseRepository;

  public List<House> findAllHouse() {
    Iterable<House> iterable = houseRepository.findAll();
    List<House> houseList = new ArrayList<>();
    iterable.forEach(houseList::add);
    return houseList;
  }

  public House findById(int id) {
    Optional<House> house = houseRepository.findById(id);
    return house.orElse(null);
  }

  public House addHouse(House house) {
    return houseRepository.save(house);
  }

  public void deleteHouse(int id) {
    houseRepository.deleteById(id);
  }

  public House updateHouse(House house) {
    return houseRepository.save(house);
  }

  public void hardDeleteHouse(int id) {
    houseRepository.hardDeleteHouse(id);
  }

  public boolean isSafeToDeleteHouse(int id) {
    return houseRepository.isSafeToDeleteHouse(id);
  }

  public boolean houseExist(String street, String zip) {
    return houseRepository.houseExist(street, zip);
  }

  public List<House> findAllByOwner(String id) {
    return houseRepository.findAllByOwner(id);
  }

  public List<House> findAllByCondition(String zip, int bed, int bath, double min, double max,
      boolean ele, boolean water, boolean gas, boolean net, boolean transit) {
    return houseRepository
        .findAllByCondition(zip, bed, bath, min, max, ele, water, gas, net, transit);
  }
}
