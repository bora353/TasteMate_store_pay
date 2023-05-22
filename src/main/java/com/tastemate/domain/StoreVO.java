package com.tastemate.domain;

import lombok.Data;

@Data
public class StoreVO {

  private String storeIdx;
  private String userIdx;
  private String storeName;
  private String category1;
  private String storeAddress;
  private String storeApi;
  private String phoneNumber;
  private String storeCount;
  private String filename;
  private String oriFilename;



  public String getStoreIdx() {
    return storeIdx;
  }

  public void setStoreIdx(String storeIdx) {
    this.storeIdx = storeIdx;
  }


  public String getUserIdx() {
    return userIdx;
  }

  public void setUserIdx(String userIdx) {
    this.userIdx = userIdx;
  }


  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }


  public String getCategory1() {
    return category1;
  }

  public void setCategory1(String category1) {
    this.category1 = category1;
  }


  public String getStoreAddress() {
    return storeAddress;
  }

  public void setStoreAddress(String storeAddress) {
    this.storeAddress = storeAddress;
  }


  public String getStoreApi() {
    return storeApi;
  }

  public void setStoreApi(String storeApi) {
    this.storeApi = storeApi;
  }


  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }


  public String getStoreCount() {
    return storeCount;
  }

  public void setStoreCount(String storeCount) {
    this.storeCount = storeCount;
  }

}
