package com.dragon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankCard {
  private String cardNumber;
  private String cardPwd;
  private String balance;
  private String category;
  private String bank;

}
