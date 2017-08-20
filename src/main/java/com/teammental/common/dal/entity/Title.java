package com.teammental.common.dal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "title")
public class Title {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  @NotNull
  @Size(max = 100)
  private String name;

  @Column(name = "is_board")
  @NotNull
  private boolean board;

  @Column(name = "client_id")
  private Integer clientId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

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
}
