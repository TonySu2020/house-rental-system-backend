package com.example.houserentalsystembackend.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class City {

  @Id
  @Column
  private String zipCode;

  @Column
  private String city;

  @Column
  private String state;

  public City() {

  }

  public City(String zipCode, String city, String state) {
    this.zipCode = zipCode;
    this.city = city;
    this.state = state;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
