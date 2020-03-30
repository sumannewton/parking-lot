package com.newton.dao;

import com.newton.model.Vehicle;
import com.newton.model.VehicleSearch;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryVehicleDao implements Dao<Vehicle> {

  /*
  Acts like a client to interact with in-memory database.
   */
  InMemoryDB database;

  public InMemoryVehicleDao(InMemoryDB database) {
    this.database = database;
    this.database.initialize();
  }

  public void save(Vehicle vehicle) {
    database.VEHICLES_TABLE.put(vehicle.getRegNo(), vehicle);
  }

  public Vehicle get(Object id) {
    return database.VEHICLES_TABLE.get(id);
  }

  public List<Vehicle> search(Object searchRequest) {
    VehicleSearch vehicleSearch = (VehicleSearch) searchRequest;
    List<Vehicle> result = database.VEHICLES_TABLE.values().stream().collect(Collectors.toList());
    if (vehicleSearch.getColour() != null) {
      result = database.filterByColour(database.VEHICLES_TABLE, vehicleSearch.getColour());
    }
    return result;
  }

  public void delete(Object id) {
    database.VEHICLES_TABLE.remove(id);
  }
}
