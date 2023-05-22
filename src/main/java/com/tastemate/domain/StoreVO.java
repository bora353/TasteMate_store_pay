package com.tastemate.domain;

import lombok.Data;

@Data
public class StoreVO {

  private String storeIdx;
  private String userIdx;
  private String storeName;
  private String category1;
  private String storeAddress;

  private double storeLati;
  private double storeLongi;

  private String phoneNumber;
  private String storeCount;
  private String filename;
  private String oriFilename;



}
