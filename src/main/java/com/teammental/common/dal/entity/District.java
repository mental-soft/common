package com.teammental.common.dal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "district")
public class District extends BaseEntity<Integer> {

  @Column(name = "name")
  @NotNull
  @Size(max = 100)
  private String name;

  @ManyToOne
  @JoinColumn(name = "city_id")
  private City city;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }
}
