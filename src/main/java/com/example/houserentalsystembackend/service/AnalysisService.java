package com.example.houserentalsystembackend.service;

import com.example.houserentalsystembackend.model.Overview;
import com.example.houserentalsystembackend.repository.AnalysisRepository;
import com.example.houserentalsystembackend.repository.CityRepository;
import com.example.houserentalsystembackend.repository.CustomerRepository;
import com.example.houserentalsystembackend.repository.HouseOwnerRepository;
import com.example.houserentalsystembackend.repository.HouseRepository;
import com.example.houserentalsystembackend.repository.LeaseRepository;
import com.example.houserentalsystembackend.repository.OwnerRepository;
import java.math.BigInteger;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalysisService {

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private HouseOwnerRepository houseOwnerRepository;

  @Autowired
  private HouseRepository houseRepository;

  @Autowired
  private LeaseRepository leaseRepository;

  @Autowired
  private OwnerRepository ownerRepository;

  @Autowired
  private AnalysisRepository analysisRepository;

  public int findNumOfCity() {
    return 10;
  }

  public int findNumOfCustomer() {
    return 20;
  }

  public int findNumOfHouse() {
    return 30;
  }

  public int findNumOfLease() {
    return 40;
  }

  public int findNumOfOwner() {
    return 50;
  }

  public Map<String, BigInteger> getOverview() {
    Map<String, BigInteger> map = analysisRepository.GET_OVERVIEW();
    return analysisRepository.GET_OVERVIEW();
  }
}
