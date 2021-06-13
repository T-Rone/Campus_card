package com.dragon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author tdragon.
 * @Date 2021/5/30.
 * @Time 18:13
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account  {
  private int  id;
  private String accountId;
  private String stuName;
  private String stuId;
  private String stuPwd;
  private String balance;
  private String college;

}
