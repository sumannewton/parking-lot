package com.newton.model.vehicle;

public abstract class AbstractVehicle {

  private VehicleType type;

  private String regNo;

  private String colour;

  private int slotId;

  public AbstractVehicle(String regNo, String colour) {
    this.type = VehicleType.FOUR_WHEELER;
    this.regNo = regNo;
    this.colour = colour;
  }

  public AbstractVehicle(VehicleType type, String regNo, String colour) {
    this.type = type;
    this.regNo = regNo;
    this.colour = colour;
  }

  public String getRegNo() {
    return regNo;
  }

  public AbstractVehicle setRegNo(String regNo) {
    this.regNo = regNo;
    return this;
  }

  public String getColour() {
    return colour;
  }

  public AbstractVehicle setColour(String colour) {
    this.colour = colour;
    return this;
  }

  public int getSlotId() {
    return slotId;
  }

  public AbstractVehicle setSlotId(int slotId) {
    this.slotId = slotId;
    return this;
  }

  public VehicleType getType() {
    return type;
  }
}
