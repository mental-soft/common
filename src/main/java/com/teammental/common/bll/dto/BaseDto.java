package com.teammental.common.bll.dto;

import com.teammental.meconfig.dto.IdDto;

import java.io.Serializable;

public abstract class BaseDto<IdT extends Serializable> implements IdDto<IdT> {

  private IdT id;

  @Override
  public IdT getId() {
    return id;
  }

  @Override
  public void setId(IdT id) {
    this.id = id;
  }
}
