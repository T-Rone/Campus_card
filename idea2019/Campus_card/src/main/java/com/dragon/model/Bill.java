package com.dragon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

  private int id;
  private Date createDate;
  private String orderId;
  private String storeOrderId;
  private String productName;
  private String price;
  private int payIncome;
  private String store;
  private String classify;

}
