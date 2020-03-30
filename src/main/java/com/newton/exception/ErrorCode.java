package com.newton.exception;

public enum ErrorCode {

  PARKING_FULL("Sorry, parking lot is full"),
  PARKING_EMPTY("Sorry, parking lot is empty"),
  VEHICLE_NOT_FOUND("Not found"),
  PARKING_LOT_EXISTS("Sorry, parking lot is already created"),
  INVALID_SLOT("Invalid slot");

  final String message;

  ErrorCode(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
