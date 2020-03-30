package com.newton.model;

import java.util.StringJoiner;

public class Slot {

  private int id;

  private Status status = Status.FREE;

  private Vehicle vehicle = null;

  public boolean isFree() {
    return status.equals(Status.FREE);
  }

  public int getId() {
    return id;
  }

  public Slot setId(int id) {
    this.id = id;
    return this;
  }

  public Status getStatus() {
    return status;
  }

  public Slot setStatus(Status status) {
    this.status = status;
    if (isFree())
      this.setVehicle(null);
    return this;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public Slot setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
    return this;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Slot.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("status=" + status)
        .add("vehicle=" + vehicle)
        .toString();
  }
}

