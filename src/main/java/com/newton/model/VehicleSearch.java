package com.newton.model;

public class VehicleSearch {

  private String colour = null;

  public String getColour() {
    return colour;
  }

  public VehicleSearch setColour(String colour) {
    this.colour = colour;
    return this;
  }

  public static VehicleSearchBuilder builder() {
    return new VehicleSearchBuilder();
  }

  public static class VehicleSearchBuilder {

    VehicleSearch vehicleSearch;

    public VehicleSearchBuilder() {
      this.vehicleSearch = new VehicleSearch();
    }

    public VehicleSearchBuilder colour(String colour) {
      vehicleSearch.setColour(colour);
      return this;
    }

    public VehicleSearch build() {
      return this.vehicleSearch;
    }
  }
}
