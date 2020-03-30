package com.newton.instruction;

import com.newton.model.vehicle.AbstractVehicle;
import com.newton.model.slot.Slot;
import com.newton.model.vehicle.VehicleFactory;
import com.newton.service.ParkingManager;
import com.newton.utils.Utils;
import java.util.List;

public class ParkingInstruction {

  final ParkingManager parkingManager;

  public ParkingInstruction(ParkingManager parkingManager) {
    this.parkingManager = parkingManager;
  }

  public void execute(String... inputs) {
    Instruction instruction = null;
    try {
      instruction = Instruction.valueOf(inputs[0].toUpperCase().replaceAll("\\s+", ""));
    } catch (IllegalArgumentException e) {
      System.out.println(String.format("Invalid instruction passed - %s", inputs[0]));
      return;
    }
    switch (instruction) {
      case CREATE_PARKING_LOT:
        //      case ADD_SLOTS:
        Integer numberOfSlots = Integer.valueOf(inputs[1]);
        parkingManager.create_parking_lot(numberOfSlots);
        System.out.println(String.format("Created a parking lot with %d slots", numberOfSlots));
        break;
      case PARK:
        AbstractVehicle vehicle =
            VehicleFactory.createVehicle("FOUR_WHEELER", inputs[1], inputs[2]);
        vehicle = parkingManager.park(vehicle);
        System.out.println(String.format("Allocated slot number: %d", vehicle.getSlotId()));
        break;
      case LEAVE:
        Slot slotFreedByRegNo = parkingManager.release(Integer.valueOf(inputs[1]));
        System.out.println(String.format("Slot number %d is free", slotFreedByRegNo.getId()));
        break;
      case STATUS:
        List<Slot> status = parkingManager.status();
        Utils.printStatus(status);
        break;
      case SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
        Slot slotForRegNo = parkingManager.getSlotForRegNo(inputs[1]);
        System.out.println(String.format("%d", slotForRegNo.getId()));
        break;
      case SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
        List<Integer> slotsForCarsWithColour = parkingManager.getSlotsForCarsWithColour(inputs[1]);
        System.out.println(slotsForCarsWithColour);
        break;
      case REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
        List<String> regNoForCarsWithColour = parkingManager.getRegNoForCarsWithColour(inputs[1]);
        System.out.println(regNoForCarsWithColour);
        break;
      default:
        System.out.println(String.format("Invalid instruction - %s", instruction.name()));
        break;
    }
  }
}
