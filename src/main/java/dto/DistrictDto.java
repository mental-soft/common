package dto;

import entity.City;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by okan on 12.02.2017.
 */
public class DistrictDto {

  private Integer id;
  private String name;
  private Boolean isActive;
  private LocalDate modifiedDate;
  private LocalDate createdDate;
  private City city;

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

  public Boolean getActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public LocalDate getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(LocalDate modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public static DistrictDtoBuilder getBuilder() {
    return new DistrictDtoBuilder();
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public static class DistrictDtoBuilder {

    private DistrictDto districtDto;

    public DistrictDtoBuilder() {
      districtDto = new DistrictDto();
    }

    public DistrictDtoBuilder id(Integer id) {
      this.districtDto.setId(id);
      return this;
    }

    public DistrictDtoBuilder name(String name) {
      this.districtDto.setName(name);
      return this;
    }

    public DistrictDtoBuilder modifiedDate(LocalDate modifiedDate) {
      this.districtDto.setModifiedDate(modifiedDate);
      return this;
    }

    public DistrictDtoBuilder createdDate(LocalDate createdDate) {
      this.districtDto.setCreatedDate(createdDate);
      return this;
    }

    public DistrictDtoBuilder active(Boolean active) {
      this.districtDto.setActive(active);
      return this;
    }

    public DistrictDtoBuilder city(City city) {
      this.districtDto.setCity(city);
      return this;
    }

    public DistrictDto build() {
      return districtDto;
    }
  }

}
