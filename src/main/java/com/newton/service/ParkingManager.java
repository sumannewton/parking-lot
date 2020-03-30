package com.newton.service;

import com.newton.dao.Dao;
import com.newton.dao.InMemorySlotDao;
import com.newton.exception.ErrorCode;
import com.newton.exception.ParkingException;
import com.newton.model.vehicle.AbstractVehicle;
import com.newton.model.slot.Slot;
import com.newton.model.SlotSearch;
import com.newton.model.slot.Status;
import com.newton.model.VehicleSearch;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class ParkingManager {

  final Dao<AbstractVehicle> vehicleDao;
  final Dao<Slot> slotDao;
  AtomicReference<Slot> nextFreeSlot;
  AtomicInteger totalSlots;
  AtomicInteger usedSlots;

  final Runnable freeSlotPicker;

  public ParkingManager(Dao<AbstractVehicle> vehicleDao, Dao<Slot> slotDao) {
    this.vehicleDao = vehicleDao;
    this.slotDao = slotDao;
    nextFreeSlot = new AtomicReference<>();
    freeSlotPicker =
        new Thread(
            () -> {
              while (nextFreeSlot.get() != null && nextFreeSlot.get().isFree()) ;
              List<Slot> freeSlots =
                  slotDao.search(SlotSearch.builder().status(Status.FREE).build());
              if (!freeSlots.isEmpty()) nextFreeSlot.set(freeSlots.get(0));
              else nextFreeSlot.set(null);
            });
  }

  public void create_parking_lot(int numberOfSlots) {
    ((InMemorySlotDao) slotDao).add(numberOfSlots);
    freeSlotPicker.run();
  }

  public AbstractVehicle park(AbstractVehicle vehicle) throws ParkingException {
    if (isParkingFull()) {
      throw new ParkingException(ErrorCode.PARKING_FULL);
    }
    // Save slot
    Slot slot = getFreeSlot();
    slot.setVehicle(vehicle);
    slot.setStatus(Status.USED);
    slotDao.save(slot);

    // Save vehicle
    vehicle.setSlotId(slot.getId());
    vehicleDao.save(vehicle);

    freeSlotPicker.run();
    return vehicle;
  }

  public Slot release(String vehicleRegNo) {
    AbstractVehicle vehicle = vehicleDao.get(vehicleRegNo);
    Slot slot = slotDao.get(vehicle.getSlotId());

    // Update slot status to FREE
    slot.setStatus(Status.FREE);
    slotDao.save(slot);
    // Remove vehicle from vehicles table
    vehicleDao.delete(vehicleRegNo);

    if (isParkingFull()) freeSlotPicker.run();

    return slot;
  }

  public Slot release(int slot_id) {
    Slot slot = slotDao.get(slot_id);

    // Remove vehicle from vehicles table
    vehicleDao.delete(slot.getVehicle().getRegNo());

    // Update slot status to FREE
    slot.setStatus(Status.FREE);
    slotDao.save(slot);

    if (isParkingFull()) freeSlotPicker.run();

    return slot;
  }

  public List<Slot> status() {
    List<Slot> status = slotDao.search(SlotSearch.builder().status(Status.USED).build());
    return status;
  }

  public Slot getSlotForRegNo(String regNo) {
    AbstractVehicle vehicle = vehicleDao.get(regNo);
    if (vehicle == null) {
      throw new ParkingException(ErrorCode.VEHICLE_NOT_FOUND);
    }
    return slotDao.get(vehicle.getSlotId());
  }

  public List<Integer> getSlotsForCarsWithColour(String colour) {
    return vehicleDao.search(VehicleSearch.builder().colour(colour).build()).stream()
        .map(AbstractVehicle::getSlotId)
        .collect(Collectors.toList());
  }

  public List<String> getRegNoForCarsWithColour(String colour) {
    return vehicleDao.search(VehicleSearch.builder().colour(colour).build()).stream()
        .map(AbstractVehicle::getRegNo)
        .collect(Collectors.toList());
  }

  public boolean isParkingFull() {
    return nextFreeSlot.get() == null;
  }

  public Slot getFreeSlot() {
    return nextFreeSlot.get();
  }

  public boolean isParkingEmpty() {
    return 0 == usedSlots.get();
  }
}
