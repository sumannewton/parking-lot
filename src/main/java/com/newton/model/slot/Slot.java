package com.newton.model.slot;

import com.newton.model.vehicle.AbstractVehicle;
import java.util.StringJoiner;

public class Slot {

  private int id;

  private Status status = Status.FREE;

  private AbstractVehicle vehicle = null;

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

  public AbstractVehicle getVehicle() {
    return vehicle;
  }

  public Slot setVehicle(AbstractVehicle vehicle) {
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

