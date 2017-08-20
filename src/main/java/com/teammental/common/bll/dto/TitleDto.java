package com.teammental.common.bll.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TitleDto extends BaseDto<Integer> {

  @NotNull
  @Size(max = 100)
  private String name;
  private boolean board;
  private Integer clientId;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isBoard() {
    return board;
  }

  public void setBoard(boolean board) {
    this.board = board;
  }

  public Integer getClientId() {
    return clientId;
  }

  public void setClientId(Integer clientId) {
    this.clientId = clientId;
  }

  @Override
  public String toString() {
    return "Id = " + getId() + ", "
        + "Name = " + getName() + ", "
        + "IsBoard = " + isBoard() + ", "
        + "ClientId = " + getClientId();
  }
}
