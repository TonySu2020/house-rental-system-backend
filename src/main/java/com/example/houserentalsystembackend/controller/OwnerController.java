package com.example.houserentalsystembackend.controller;

import com.example.houserentalsystembackend.model.BaseResponse;
import com.example.houserentalsystembackend.model.entity.Owner;
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

  @PostMapping(value = "/api/owners")
  public BaseResponse<Owner> addOwner(@RequestBody Owner owner) {
    try {
      if (ownerService.findById(owner.getId()) != null) {
        return new BaseResponse<>(409, null, "This owner has been registered.");
      }
      Owner newOwner = ownerService.addOwner(owner);
      return new BaseResponse<>(200, newOwner, "Owner Added");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

  @GetMapping(value = "/api/owners/{id}")
  public BaseResponse<Owner> findById(@PathVariable("id") String id) {
    try {
      Owner owner = ownerService.findById(id);
      if (owner == null) {
        return new BaseResponse<>(404, null, "No Such Owner");
      }
      return new BaseResponse<>(200, owner, "Found Owner");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

  @DeleteMapping(value = "/api/owners/{id}")
  public BaseResponse<Owner> deleteById(@PathVariable("id") String id) {
    try {
      Owner owner = ownerService.findById(id);
      if (owner == null) {
        return new BaseResponse<>(404, null, "No Such Owner");
      }
      if (!ownerService.isSafeToDeleteOwner(id)) {
        return new BaseResponse<>(403, null,
            "Delete Failed! This owner has houses! If you want to delete it anyway, use HARD DELETE instead.");
      }
      ownerService.deleteOwner(id);
      return new BaseResponse<>(200, null, "Owner Deleted");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

  @PutMapping(value = "/api/owners/{id}")
  public BaseResponse<Owner> updateById(@PathVariable("id") String id, @RequestBody Owner owner) {
    try {
      Owner oldOwner = ownerService.findById(id);
      if (oldOwner == null) {
        return new BaseResponse<>(404, null, "No Such Owner");
      }
      Owner newOwner;
      oldOwner.setId(owner.getId());
      oldOwner.setEmail(owner.getEmail());
      oldOwner.setFirstName(owner.getFirstName());
      oldOwner.setLastName(owner.getLastName());
      oldOwner.setPhone(owner.getPhone());

      newOwner = ownerService.updateOwner(oldOwner);

      return new BaseResponse<>(200, newOwner, "Owner Updated");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

  @GetMapping(value = "/api/owners/email/{email}")
  public BaseResponse<List<Owner>> findByEmail(@PathVariable("email") String email) {
    try {
      List<Owner> ownerList = ownerService.findByEmail(email);
      return new BaseResponse<>(200, ownerList, "Found Owners");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }


  @GetMapping(value = "/api/owners/phone/{phone}")
  public BaseResponse<List<Owner>> findByPhone(@PathVariable("phone") String phone) {
    try {
      List<Owner> ownerList = ownerService.findByPhone(phone);
      return new BaseResponse<>(200, ownerList, "Found Owners");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }

  @DeleteMapping(value = "/api/owners/{id}/hard")
  public BaseResponse<Owner> hardDeleteById(@PathVariable("id") String id) {
    try {
      Owner owner = ownerService.findById(id);
      if (owner == null) {
        return new BaseResponse<>(404, null, "No Such Owner");
      }
      ownerService.HardDeleteOwner(id);
      return new BaseResponse<>(200, null, "Owner Deleted");
    } catch (Exception e) {
      return new BaseResponse<>(500, null, e.getMessage());
    }
  }
}
