package com.example.houserentalsystembackend.controller;

import com.example.houserentalsystembackend.model.BaseResponse;
import com.example.houserentalsystembackend.model.entity.City;
import com.example.houserentalsystembackend.service.CityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
public class CityController {

  @Autowired
  private CityService cityService;

  @GetMapping(value = "/api/cities")
  public BaseResponse<List<City>> findAllCity() {
    try {
      List<City> cityList = cityService.findAllCity();
      return new BaseResponse<>(200, cityList, "Found Cities");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

  @PostMapping(value = "/api/cities")
  public BaseResponse<City> addCity(@RequestBody City city) {
    try {
      if (cityService.findById(city.getZipCode()) == null) {
        return new BaseResponse<>(409, null, "This city has been added.");
      }
      City newCity = cityService.addCity(city);
      return new BaseResponse<>(200, newCity, "City Added");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }
}
