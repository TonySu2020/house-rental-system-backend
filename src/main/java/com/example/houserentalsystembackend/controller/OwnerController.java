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
      return BaseResponse.ok(ownerList, "Found owners");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }

  }

  @PostMapping(value = "/api/owners")
  public BaseResponse<Owner> addOwner(@RequestBody Owner owner) {
    try {
      if (ownerService.findById(owner.getId()) != null) {
        return BaseResponse.error(409, "This owner has been registered.");
      }
      Owner newOwner = ownerService.addOwner(owner);
      return BaseResponse.ok(newOwner, "Owner Added");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }

  @GetMapping(value = "/api/owners/{id}")
  public BaseResponse<Owner> findById(@PathVariable("id") String id) {
    try {
      Owner owner = ownerService.findById(id);
      if (owner == null) {
        return BaseResponse.error(404, "No Such Owner");
      }
      return BaseResponse.ok(owner, "Found Owner");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }

  @DeleteMapping(value = "/api/owners/{id}")
  public BaseResponse<Owner> deleteById(@PathVariable("id") String id) {
    try {
      Owner owner = ownerService.findById(id);
      if (owner == null) {
        return BaseResponse.error(404, "No Such Owner");
      }
      if (!ownerService.isSafeToDeleteOwner(id)) {
        return BaseResponse.error(403,
          "Delete Failed! This owner has houses! If you want to delete it anyway, use HARD DELETE instead.");
      }
      ownerService.deleteOwner(id);
      return BaseResponse.ok(null, "Owner Deleted");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }

  @PutMapping(value = "/api/owners/{id}")
  public BaseResponse<Owner> updateById(@PathVariable("id") String id, @RequestBody Owner owner) {
    try {
      Owner oldOwner = ownerService.findById(id);
      if (oldOwner == null) {
        return BaseResponse.error(404, "No Such Owner");
      }
      Owner newOwner;
      oldOwner.setId(owner.getId());
      oldOwner.setEmail(owner.getEmail());
      oldOwner.setFirstName(owner.getFirstName());
      oldOwner.setLastName(owner.getLastName());
      oldOwner.setPhone(owner.getPhone());

      newOwner = ownerService.updateOwner(oldOwner);

      return BaseResponse.ok(newOwner, "Owner Updated");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }

  @GetMapping(value = "/api/owners/email/{email}")
  public BaseResponse<List<Owner>> findByEmail(@PathVariable("email") String email) {
    try {
      List<Owner> ownerList = ownerService.findByEmail(email);
      return BaseResponse.ok(ownerList, "Found Owners");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }


  @GetMapping(value = "/api/owners/phone/{phone}")
  public BaseResponse<List<Owner>> findByPhone(@PathVariable("phone") String phone) {
    try {
      List<Owner> ownerList = ownerService.findByPhone(phone);
      return BaseResponse.ok(ownerList, "Found Owners");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }

  @DeleteMapping(value = "/api/owners/{id}/hard")
  public BaseResponse<Owner> hardDeleteById(@PathVariable("id") String id) {
    try {
      Owner owner = ownerService.findById(id);
      if (owner == null) {
        return BaseResponse.error(404, "No Such Owner");
      }
      ownerService.hardDeleteOwner(id);
      return BaseResponse.ok(null, "Owner Deleted");
    } catch (Exception e) {
      return BaseResponse.error(e.getMessage());
    }
  }
}
