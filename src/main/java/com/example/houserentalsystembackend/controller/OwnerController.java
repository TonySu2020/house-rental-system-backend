package com.example.houserentalsystembackend.controller;

import com.example.houserentalsystembackend.model.BaseResponse;
import com.example.houserentalsystembackend.model.entity.Owner;
import com.example.houserentalsystembackend.service.OwnerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
public class OwnerController {

  @Autowired
  private OwnerService ownerService;

  @GetMapping(value = "/api/owners")
  public BaseResponse<List<Owner>> findAllOwner() {
    try {
      List<Owner> ownerList = ownerService.findAllOwner();
      return new BaseResponse<>(200, ownerList, "Found owners");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());

    }

  }

}
