package com.newton.exception;

public class ParkingException extends RuntimeException {

  private static final String DEFAULT_MSG = "Exception occurred";
  private ErrorCode errorCode = null;

  public ParkingException() {
    super(DEFAULT_MSG);
  }

  public ParkingException(ErrorCode errorCode) {
    super(DEFAULT_MSG);
    this.setErrorCode(errorCode);
  }

  /**
   * @param cause to throw
   * @param errorCode
   */
  public ParkingException(final Throwable cause, ErrorCode errorCode) {
    super(DEFAULT_MSG, cause);
    this.setErrorCode(errorCode);
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  @Override
  public String getMessage() {
    return getErrorCode().getMessage();
  }
}