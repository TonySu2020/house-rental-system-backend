package com.example.houserentalsystembackend.model.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Lease {

  @Id
  private String id;

  private Date startDate;

  private Date endDate;

  private double actualRent;

  @ManyToOne
  private Agent agent;

  @ManyToOne
  private Customer customer;

  @ManyToOne
  private HouseOwner houseOwner;

  public Lease() {
  }

  public Lease(String id, Date startDate, Date endDate, double actualRent,
      Agent agent, Customer customer,
      HouseOwner houseOwner) {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.actualRent = actualRent;
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

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public double getActualRent() {
    return actualRent;
  }

  public void setActualRent(double actualRent) {
    this.actualRent = actualRent;
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
