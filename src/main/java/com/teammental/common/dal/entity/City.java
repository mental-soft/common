package com.teammental.common.dal.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "city")
public class City extends BaseEntity<Integer> {

  @Column(name = "name")
  @NotNull
  @Size(max = 50)
  private String name;

  @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
  private List<District> districts;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<District> getDistricts() {
    return districts;
  }

  public void setDistricts(List<District> districts) {
    this.districts = districts;
  }
}
