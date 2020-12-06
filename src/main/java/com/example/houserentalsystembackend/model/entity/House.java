package com.example.houserentalsystembackend.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class House {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private double rent;

  private String street;

  @ManyToOne
  private City city;

  @ManyToOne
  private Owner owner;

  private int bedroomNumber;

  private int bathroomNumber;

  private boolean nearToTransit;

  private boolean electricityInclude;

  private boolean waterInclude;

  private boolean gasInclude;

  private boolean networkInclude;

  private String note;

  public House() {
  }

  public House(int id, double rent, String street,
      City city, Owner owner, int bedroomNumber, int bathroomNumber, boolean nearToTransit,
      boolean electricityInclude, boolean waterInclude, boolean gasInclude, boolean networkInclude,
      String note) {
    this.id = id;
    this.rent = rent;
    this.street = street;
    this.city = city;
    this.owner = owner;
    this.bedroomNumber = bedroomNumber;
    this.bathroomNumber = bathroomNumber;
    this.nearToTransit = nearToTransit;
    this.electricityInclude = electricityInclude;
    this.waterInclude = waterInclude;
    this.gasInclude = gasInclude;
    this.networkInclude = networkInclude;
    this.note = note;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getRent() {
    return rent;
  }

  public void setRent(double rent) {
    this.rent = rent;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public City getCity() {
    return city;
  }

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public int getBedroomNumber() {
    return bedroomNumber;
  }

  public void setBedroomNumber(int bedroomNumber) {
    this.bedroomNumber = bedroomNumber;
  }

  public int getBathroomNumber() {
    return bathroomNumber;
  }

  public void setBathroomNumber(int bathroomNumber) {
    this.bathroomNumber = bathroomNumber;
  }

  public boolean isNearToTransit() {
    return nearToTransit;
  }

  public void setNearToTransit(boolean nearToTransit) {
    this.nearToTransit = nearToTransit;
  }

  public boolean isElectricityInclude() {
    return electricityInclude;
  }

  public void setElectricityInclude(boolean electricityInclude) {
    this.electricityInclude = electricityInclude;
  }

  public boolean isWaterInclude() {
    return waterInclude;
  }

  public void setWaterInclude(boolean waterInclude) {
    this.waterInclude = waterInclude;
  }

  public boolean isGasInclude() {
    return gasInclude;
  }

  public void setGasInclude(boolean gasInclude) {
    this.gasInclude = gasInclude;
  }

  public boolean isNetworkInclude() {
    return networkInclude;
  }

  public void setNetworkInclude(boolean networkInclude) {
    this.networkInclude = networkInclude;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }
}
