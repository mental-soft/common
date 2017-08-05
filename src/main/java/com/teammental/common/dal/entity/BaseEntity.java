package com.teammental.common.dal.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity<IdT extends Number> {

  @Column(name = "id")
  @Id
  private IdT id;

  public IdT getId() {
    return id;
  }

  public void setId(IdT id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return id.toString();
  }
}
