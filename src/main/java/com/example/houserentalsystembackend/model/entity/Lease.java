package com.example.houserentalsystembackend.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Lease {

  @Id
  private String id;

  @ManyToOne
  private Agent agent;

  @ManyToOne
  private Customer customer;

  @ManyToOne
  private HouseOwner houseOwner;

  public Lease() {
  }

  public Lease(String id, Agent agent,
      Customer customer, HouseOwner houseOwner) {
    this.id = id;
    this.agent = agent;
    this.customer = customer;
    this.houseOwner = houseOwner;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Agent getAgent() {
    return agent;
  }

  public void setAgent(Agent agent) {
    this.agent = agent;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public HouseOwner getHouseOwner() {
    return houseOwner;
  }

  public void setHouseOwner(HouseOwner houseOwner) {
    this.houseOwner = houseOwner;
  }
}
