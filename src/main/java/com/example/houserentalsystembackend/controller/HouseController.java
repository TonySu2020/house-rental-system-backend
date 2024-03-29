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
import org.springframework.web.bind.annotation.RequestParam;
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
      return BaseResponse.ok(houseList, "Found Houses");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }

  @PostMapping(value = "/api/houses")
  public BaseResponse<House> addHouse(@RequestBody House house) {
    try {
      if (houseService.findById(house.getId()) != null) {
        return BaseResponse.error(409, "This house has been registered.");
      }
      if (houseService.houseExist(house.getStreet(), house.getCity().getZipCode())) {
        return BaseResponse.error(409, "This house has been registered.");
      }
      City city = cityService.findById(house.getCity().getZipCode());
      if (city == null) {
        city = cityService.addCity(house.getCity());
      }
      house.setCity(city);
      Owner owner = ownerService.findById(house.getOwner().getId());
      if (owner == null) {
        return BaseResponse.error(404, "No Such Owner");
      }
      house.setOwner(owner);
      House newHouse = houseService.addHouse(house);
      return BaseResponse.ok(newHouse, "House Added");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }

  @GetMapping(value = "/api/houses/{id}")
  public BaseResponse<House> findById(@PathVariable("id") int id) {
    try {
      House house = houseService.findById(id);
      if (house == null) {
        return BaseResponse.error(404, "No Such House");
      }
      return BaseResponse.ok(house, "Found House");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }

  @DeleteMapping(value = "/api/houses/{id}")
  public BaseResponse<House> deleteById(@PathVariable("id") int id) {
    try {
      House house = houseService.findById(id);
      if (house == null) {
        return BaseResponse.error(404, "No Such House");
      }
      if (!houseService.isSafeToDeleteHouse(id)) {
        return BaseResponse.error(403,
          "Delete Failed! This house has leases! If you want to delete it anyway, use HARD DELETE instead.");
      }
      houseService.deleteHouse(id);
      return BaseResponse.ok(null, "House Deleted");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }

  @PutMapping(value = "/api/houses/{id}")
  public BaseResponse<House> updateById(@PathVariable("id") int id, @RequestBody House house) {
    try {
      House oldHouse = houseService.findById(id);
      if (oldHouse == null) {
        return BaseResponse.error(404, "No Such House");
      }

      City city = cityService.findById(house.getCity().getZipCode());
      if (city == null) {
        city = cityService.addCity(house.getCity());
      }
      house.setCity(city);

      Owner owner = ownerService.findById(house.getOwner().getId());
      if (owner == null) {
        return BaseResponse.error(404, "No Such Owner");
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

      return BaseResponse.ok(newHouse, "House Updated");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }

  @DeleteMapping(value = "/api/houses/{id}/hard")
  public BaseResponse<House> hardDeleteById(@PathVariable("id") int id) {
    try {
      House house = houseService.findById(id);
      if (house == null) {
        return BaseResponse.error(404, "No Such House");
      }
      houseService.hardDeleteHouse(id);
      return BaseResponse.ok(null, "House Deleted");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }

  @GetMapping(value = "/api/houses/owner/{id}")
  public BaseResponse<List<House>> findAllByOwner(@PathVariable("id") String id) {
    try {
      List<House> houseList = houseService.findAllByOwner(id);
      return BaseResponse.ok(houseList, "Found Houses");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }

  @GetMapping(value = "/api/houses/condition")
  public BaseResponse<List<House>> findAllByCondition(@RequestParam("zip") String zip,
    @RequestParam("bed") int bed, @RequestParam("bath") int bath,
    @RequestParam("min") double min, @RequestParam("max") double max,
    @RequestParam("ele") boolean ele, @RequestParam("water") boolean water,
    @RequestParam("gas") boolean gas, @RequestParam("net") boolean net,
    @RequestParam("transit") boolean transit) {
    try {
      List<House> houseList = houseService
        .findAllByCondition(zip, bed, bath, min, max, ele, water, gas, net, transit);
      return BaseResponse.ok(houseList, "Found Houses");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }
}
