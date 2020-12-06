package com.example.houserentalsystembackend.model.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Lease {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private Date startDate;

  private Date endDate;

  private double actualRent;

  @ManyToOne
  private Agent agent;

  @ManyToOne
  private Customer customer;

  @ManyToOne
  private House house;

  public Lease() {
  }

  public Lease(int id, Date startDate, Date endDate, double actualRent,
      Agent agent, Customer customer,
      House house) {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.actualRent = actualRent;
    this.agent = agent;
    this.customer = customer;
    this.house = house;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public House getHouse() {
    return house;
  }

  public void setHouse(House house) {
    this.house = house;
  }
}
