package com.example.houserentalsystembackend.service;

import com.example.houserentalsystembackend.repository.AnalysisRepository;
import java.math.BigInteger;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalysisService {

  @Autowired
  private AnalysisRepository analysisRepository;

  public Map<String, BigInteger> getOverview() {
    return analysisRepository.GET_OVERVIEW();
  }
}
