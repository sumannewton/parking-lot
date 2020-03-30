package com.newton.instruction;

import com.newton.model.Slot;
import com.newton.model.Vehicle;
import com.newton.service.ParkingManager;
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
      instruction = Instruction.INVALID_INSTRUCTION;
    }
    switch (instruction) {
      case CREATE_PARKING_LOT:
        //      case ADD_SLOTS:
        Integer numberOfSlots = Integer.valueOf(inputs[1]);
        parkingManager.create_parking_lot(numberOfSlots);
        System.out.println(String.format("Created a parking lot with %d slots", numberOfSlots));
        break;
      case PARK:
        Vehicle vehicle = new Vehicle(inputs[1], inputs[2]);
        vehicle = parkingManager.park(vehicle);
        System.out.println(String.format("Allocated slot number: %d", vehicle.getSlotId()));
        break;
      case LEAVE:
        Slot slotFreedByRegNo = parkingManager.release(Integer.valueOf(inputs[1]));
        System.out.println(String.format("Slot number %d is free", slotFreedByRegNo.getId()));
        break;
        //      case RELEASE_VEHICLE_BY_SLOT_ID:
        //        Slot slotFreedBySlotId = parkingManager.release(Integer.valueOf(inputs[1]));
        //        System.out.println(String.format("Slot number %d is free",
        // slotFreedBySlotId.getId()));
        //        break;
      case STATUS:
        List<Slot> status = parkingManager.status();
        display(status);
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

  private void display(List<Slot> status) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Slot No\t|\tRegistration No\t|\tColour\n");
    stringBuilder.append("---------------------------------------").append("\n");
    status.forEach(
        slot ->
            stringBuilder
                .append(
                    String.format(
                        "\t\t%d\t\t|\t%s\t\t|\t%s\t\t\t\t",
                        slot.getId(), slot.getVehicle().getRegNo(), slot.getVehicle().getColour()))
                .append("\n"));
    System.out.println(stringBuilder.toString());
  }
}
