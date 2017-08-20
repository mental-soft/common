package com.teammental.common.bll.dto;

public abstract class BaseDto<IdT> implements Dto {

  private Integer id;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
