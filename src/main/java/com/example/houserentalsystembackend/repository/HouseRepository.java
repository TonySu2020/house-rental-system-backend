package com.example.houserentalsystembackend.repository;

import com.example.houserentalsystembackend.model.entity.House;
import org.springframework.data.repository.CrudRepository;

public interface HouseRepository extends CrudRepository<House, String> {

}
