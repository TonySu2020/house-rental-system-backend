package com.example.houserentalsystembackend.service;

import com.example.houserentalsystembackend.model.entity.City;
import com.example.houserentalsystembackend.repository.CityRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

  @Autowired
  private CityRepository cityRepository;

  public List<City> findAllCity() {
    List<City> cityList = new ArrayList<>();
    Iterable<City> iterable = cityRepository.findAll();
    iterable.forEach(cityList::add);
    return cityList;
  }

}
