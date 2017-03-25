package dto;

import java.time.LocalDateTime;


/**
 * Created by okan on 12.02.2017.
 */
public class DistrictDto {
  private Integer id;
  private String name;
  private Boolean isActive;
  private LocalDateTime modifiedDate;
  private LocalDateTime createdDate;
  private CityDto cityDto;


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

  public LocalDateTime getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(LocalDateTime modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public static DistrictDtoBuilder getBuilder() {
    return new DistrictDtoBuilder();
  }

  public CityDto getCityDto() {
    return cityDto;
  }

  public void setCityDto(CityDto cityDto) {
    this.cityDto = cityDto;
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

    public DistrictDtoBuilder modifiedDate(LocalDateTime modifiedDate) {
      this.districtDto.setModifiedDate(modifiedDate);
      return this;
    }

    public DistrictDtoBuilder createdDate(LocalDateTime createdDate) {
      this.districtDto.setCreatedDate(createdDate);
      return this;
    }

    public DistrictDtoBuilder active(Boolean active) {
      this.districtDto.setActive(active);
      return this;
    }

    public DistrictDtoBuilder cityDto(CityDto cityDto) {
      this.districtDto.setCityDto(cityDto);
      return this;
    }

    public DistrictDto build() {
      return districtDto;
    }

  }
}


