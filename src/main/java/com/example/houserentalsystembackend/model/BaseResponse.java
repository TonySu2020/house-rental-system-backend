package com.example.houserentalsystembackend.model;

public class BaseResponse<T> {

  protected int responseCode;
  protected T responseObj;
  protected String message;

  public BaseResponse(int responseCode, T responseObj, String message) {
    this.responseCode = responseCode;
    this.responseObj = responseObj;
    this.message = message;
  }

  public int getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(int responseCode) {
    this.responseCode = responseCode;
  }

  public T getResponseObj() {
    return responseObj;
  }

  public void setResponseObj(T responseObj) {
    this.responseObj = responseObj;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
