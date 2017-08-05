package com.teammental.common.dal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "generic_value")
public class GenericValue extends BaseEntity<Integer> {

  @Column(name = "generic_type_id")
  @NotNull
  private short genericTypeId;

  @Size(max = 500)
  @NotNull
  private String data;

  public short getGenericTypeId() {
    return genericTypeId;
  }

  public void setGenericTypeId(short genericTypeId) {
    this.genericTypeId = genericTypeId;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
}
