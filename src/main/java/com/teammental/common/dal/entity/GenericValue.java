package com.teammental.common.dal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "generic_value")
public class GenericValue {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "generic_type_id")
  @NotNull
  private short genericTypeId;

  @Size(max = 500)
  @NotNull
  private String data;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

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
