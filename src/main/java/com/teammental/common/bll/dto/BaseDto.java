package com.teammental.common.bll.dto;

import com.teammental.meconfig.dto.IdDto;

public abstract class BaseDto<IdT> implements IdDto<IdT> {

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
