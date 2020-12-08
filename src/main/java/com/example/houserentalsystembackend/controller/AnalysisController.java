package com.example.houserentalsystembackend.controller;

import com.example.houserentalsystembackend.model.BaseResponse;
import com.example.houserentalsystembackend.model.Overview;
import com.example.houserentalsystembackend.service.AnalysisService;
import java.math.BigInteger;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
public class AnalysisController {

  @Autowired
  private AnalysisService analysisService;

  @GetMapping(value = "/api/analysis/overview")
  public BaseResponse<Overview> findAllCount() {

    try{
      Map<String, BigInteger> map = analysisService.getOverview();
      Overview overview = new Overview();
      overview.setNumOfCity(map.get("num_of_city").intValue());
      overview.setNumOfCustomer(map.get("num_of_customer").intValue());
      overview.setNumOfHouse(map.get("num_of_house").intValue());
      overview.setNumOfLease(map.get("num_of_lease").intValue());
      overview.setNumOfOwner(map.get("num_of_owner").intValue());

      return new BaseResponse<>(200, overview, "Found Summary");
    } catch (Exception e){
      return new BaseResponse<>(500, null, e.getMessage());
    }

  }

}
