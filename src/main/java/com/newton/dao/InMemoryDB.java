package com.newton.dao;

import com.newton.model.Slot;
import com.newton.model.Status;
import com.newton.model.Vehicle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryDB {

  static List<Slot> SLOTS_TABLE;
  static Map<String, Vehicle> VEHICLES_TABLE;

  protected void initialize() {
    if (SLOTS_TABLE == null)
      SLOTS_TABLE = Collections.synchronizedList(new ArrayList<>());
    if (VEHICLES_TABLE == null)
      VEHICLES_TABLE = new ConcurrentHashMap<>();
    return;
  }

  public List<Slot> filterByStatus(List<Slot> input, Status status) {
    return input.stream()
        .filter(slot -> slot.getStatus().equals(status))
        .collect(Collectors.toList());
  }

  public List<Vehicle> filterByColour(Map<String, Vehicle> input, String colour) {
    return input.entrySet()
        .stream()
        .filter(stringVehicleEntry -> stringVehicleEntry.getValue().getColour().equalsIgnoreCase(colour))
        .map(Entry::getValue)
        .collect(Collectors.toList());
  }

  public Vehicle getByRegId(String reg_no) {
    return VEHICLES_TABLE.get(reg_no);
  }
}
