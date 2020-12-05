package com.example.houserentalsystembackend.controller;

import com.example.houserentalsystembackend.model.BaseResponse;
import com.example.houserentalsystembackend.model.entity.House;
import com.example.houserentalsystembackend.service.HouseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
public class HouseController {

  @Autowired
  private HouseService houseService;

  @GetMapping(value = "/api/houses")
  public BaseResponse<List<House>> findAllHouse() {
    try {
      List<House> houseList = houseService.findAllHouse();
      return new BaseResponse<>(200, houseList, "Found Houses");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }
}
