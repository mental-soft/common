package com.teammental.common.bll.dto;

public class IdNameDto extends BaseDto<Integer> {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Id = " + getId() + ", Name = " + name ;
  }
}
