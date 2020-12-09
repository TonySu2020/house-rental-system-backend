package com.example.houserentalsystembackend.controller;

import com.example.houserentalsystembackend.model.BaseResponse;
import com.example.houserentalsystembackend.model.entity.City;
import com.example.houserentalsystembackend.model.entity.Customer;
import com.example.houserentalsystembackend.model.entity.House;
import com.example.houserentalsystembackend.model.entity.Owner;
import com.example.houserentalsystembackend.service.CityService;
import com.example.houserentalsystembackend.service.HouseService;
import com.example.houserentalsystembackend.service.OwnerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
public class HouseController {

  @Autowired
  private HouseService houseService;

  @Autowired
  private CityService cityService;

  @Autowired
  private OwnerService ownerService;

  @GetMapping(value = "/api/houses")
  public BaseResponse<List<House>> findAllHouse() {
    try {
      List<House> houseList = houseService.findAllHouse();
      return new BaseResponse<>(200, houseList, "Found Houses");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

  @PostMapping(value = "/api/houses")
  public BaseResponse<House> addHouse(@RequestBody House house) {
    try {
      if (houseService.findById(house.getId()) != null) {
        return new BaseResponse<>(409, null, "This house has been registered.");
      }
      City city = cityService.findById(house.getCity().getZipCode());
      if (city == null) {
        city = cityService.addCity(house.getCity());
      }
      house.setCity(city);
      Owner owner = ownerService.findById(house.getOwner().getId());
      if (owner == null) {
        return new BaseResponse<>(404, null, "No Such Owner");
      }
      house.setOwner(owner);
      House newHouse = houseService.addHouse(house);
      return new BaseResponse<>(200, newHouse, "House Added");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

  @GetMapping(value = "/api/houses/{id}")
  public BaseResponse<House> findById(@PathVariable("id") int id) {
    try {
      House house = houseService.findById(id);
      if (house == null) {
        return new BaseResponse<>(404, null, "No Such House");
      }
      return new BaseResponse<>(200, house, "Found House");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

  @DeleteMapping(value = "/api/houses/{id}")
  public BaseResponse<House> deleteById(@PathVariable("id") int id) {
    try {
      House house = houseService.findById(id);
      if (house == null) {
        return new BaseResponse<>(404, null, "No Such House");
      }
      if (!houseService.isSafeToDeleteHouse(id)) {
        return new BaseResponse<>(403, null,
            "Delete Failed! This house has leases! If you want to delete it anyway, use HARD DELETE instead.");
      }
      houseService.deleteHouse(id);
      return new BaseResponse<>(200, null, "House Deleted");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

  @PutMapping(value = "/api/houses/{id}")
  public BaseResponse<House> updateById(@PathVariable("id") int id, @RequestBody House house) {
    try {
      House oldHouse = houseService.findById(id);
      if (oldHouse == null) {
        return new BaseResponse<>(404, null, "No Such House");
      }

      City city = cityService.findById(house.getCity().getZipCode());
      if (city == null) {
        city = cityService.addCity(house.getCity());
      }
      house.setCity(city);

      Owner owner = ownerService.findById(house.getOwner().getId());
      if (owner == null) {
        return new BaseResponse<>(404, null, "No Such Owner");
      }
      house.setOwner(owner);

      oldHouse.setId(house.getId());
      oldHouse.setOwner(house.getOwner());
      oldHouse.setCity(house.getCity());
      oldHouse.setBathroomNumber(house.getBathroomNumber());
      oldHouse.setBedroomNumber(house.getBedroomNumber());
      oldHouse.setElectricityInclude(house.isElectricityInclude());
      oldHouse.setWaterInclude(house.isWaterInclude());
      oldHouse.setGasInclude(house.isGasInclude());
      oldHouse.setNetworkInclude(house.isNetworkInclude());
      oldHouse.setNearToTransit(house.isNearToTransit());
      oldHouse.setRent(house.getRent());
      oldHouse.setNote(house.getNote());
      oldHouse.setStreet(house.getStreet());

      House newHouse = houseService.updateHouse(oldHouse);

      return new BaseResponse<>(200, newHouse, "House Updated");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

  @DeleteMapping(value = "/api/houses/{id}/hard")
  public BaseResponse<House> hardDeleteById(@PathVariable("id") int id) {
    try {
      House house = houseService.findById(id);
      if (house == null) {
        return new BaseResponse<>(404, null, "No Such House");
      }
      houseService.hardDeleteHouse(id);
      return new BaseResponse<>(200, null, "House Deleted");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

  @GetMapping(value = "/api/houses/owner/{id}")
  public BaseResponse<List<House>> findAllByOwner(@PathVariable("id") String id) {
    try {
      List<House> houseList = houseService.findAllByOwner(id);
      return new BaseResponse<>(200, houseList, "Found Houses");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }
}
