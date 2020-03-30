package com.newton.model;

public class Vehicle {

  private String regNo;

  private String colour;

  private int slotId;

  public Vehicle(String regNo, String colour) {
    this.regNo = regNo;
    this.colour = colour;
  }

  public String getRegNo() {
    return regNo;
  }

  public Vehicle setRegNo(String regNo) {
    this.regNo = regNo;
    return this;
  }

  public String getColour() {
    return colour;
  }

  public Vehicle setColour(String colour) {
    this.colour = colour;
    return this;
  }

  public int getSlotId() {
    return slotId;
  }

  public Vehicle setSlotId(int slotId) {
    this.slotId = slotId;
    return this;
  }
}
