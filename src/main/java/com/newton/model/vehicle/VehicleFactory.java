package com.newton.model.vehicle;

public class VehicleFactory {

  public static AbstractVehicle createVehicle(String type, String regNo, String colour) {
    VehicleType vehicleType = VehicleType.valueOf(type);
    AbstractVehicle vehicle = null;
    switch (vehicleType) {
      case TWO_WHEELER:
        // TODO: Implement two wheeler instantiation
      case FOUR_WHEELER:
        vehicle = new FourWheeler(regNo, colour);
        break;
      default:
        break;
    }
    return vehicle;
  }

}
