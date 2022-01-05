package com.example.houserentalsystembackend.model;

public class BaseResponse<T> {

  protected int responseCode;
  protected T responseObj;
  protected String message;

  public static <K> BaseResponse<K> ok(String message) {
    return new BaseResponse<>(200, null, message);
  }

  public static <K> BaseResponse<K> ok(K responseObj, String message) {
    return new BaseResponse<>(200, responseObj, message);
  }

  public static <K> BaseResponse<K> error(String message) {
    return new BaseResponse<>(500, null, message);
  }

  public static <K> BaseResponse<K> error(K responseObj, String message) {
    return new BaseResponse<>(500, responseObj, message);
  }

  public static <K> BaseResponse<K> error(int responseCode, String message) {
    return new BaseResponse<>(responseCode, null, message);
  }

  public static <K> BaseResponse<K> error(int responseCode, K responseObj, String message) {
    return new BaseResponse<>(responseCode, responseObj, message);
  }

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
