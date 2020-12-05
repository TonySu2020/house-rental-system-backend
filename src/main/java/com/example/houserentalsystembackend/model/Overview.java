package com.example.houserentalsystembackend.model;

public class Overview {

  private int numOfCustomer;

  private int numOfOwner;

  private int numOfCity;

  private int numOfHouse;

  private int numOfLease;

  public Overview() {
  }

  public Overview(int numOfCustomer, int numOfOwner, int numOfCity, int numOfHouse, int numOfLease) {
    this.numOfCustomer = numOfCustomer;
    this.numOfOwner = numOfOwner;
    this.numOfCity = numOfCity;
    this.numOfHouse = numOfHouse;
    this.numOfLease = numOfLease;
  }

  public int getNumOfCustomer() {
    return numOfCustomer;
  }

  public void setNumOfCustomer(int numOfCustomer) {
    this.numOfCustomer = numOfCustomer;
  }

  public int getNumOfOwner() {
    return numOfOwner;
  }

  public void setNumOfOwner(int numOfOwner) {
    this.numOfOwner = numOfOwner;
  }

  public int getNumOfCity() {
    return numOfCity;
  }

  public void setNumOfCity(int numOfCity) {
    this.numOfCity = numOfCity;
  }

  public int getNumOfHouse() {
    return numOfHouse;
  }

  public void setNumOfHouse(int numOfHouse) {
    this.numOfHouse = numOfHouse;
  }

  public int getNumOfLease() {
    return numOfLease;
  }

  public void setNumOfLease(int numOfLease) {
    this.numOfLease = numOfLease;
  }
}
