package dto;

import java.time.LocalDateTime;

/**
 * Bu sınıf ülke listesinde kullanılacak.
 * Ülkenin idsini, adını ve ingilizce adını,
 * ülke kodunu ve aktif olup olmadığını sağlar.
 * Created by Coşkun on 4.2.2017.
 */
public class CountryDto {

  private Integer id;
  private String name;
  private String enName;
  private String code;
  private Boolean isActive;
  private LocalDateTime modifiedDate;
  private LocalDateTime createdDate;

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

  public String getEnName() {
    return enName;
  }

  public void setEnName(String enName) {
    this.enName = enName;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean active) {
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

  public static CountryDtoBuilder getBuilder() {
    return new CountryDtoBuilder();
  }

  public static class CountryDtoBuilder {

    private CountryDto countryDto;

    public CountryDtoBuilder() {
      countryDto = new CountryDto();
    }

    public CountryDtoBuilder id(Integer id) {
      this.countryDto.setId(id);
      return this;
    }

    public CountryDtoBuilder name(String name) {
      this.countryDto.setName(name);
      return this;
    }

    public CountryDtoBuilder enName(String enName) {
      this.countryDto.setEnName(enName);
      return this;
    }

    public CountryDtoBuilder code(String code) {
      this.countryDto.setCode(code);
      return this;
    }

    public CountryDtoBuilder modifiedDate(LocalDateTime modifiedDate) {
      this.countryDto.setModifiedDate(modifiedDate);
      return this;
    }

    public CountryDtoBuilder createdDate(LocalDateTime createdDate) {
      this.countryDto.setCreatedDate(createdDate);
      return this;
    }

    public CountryDtoBuilder active(Boolean active) {
      this.countryDto.setIsActive(active);
      return this;
    }

    public CountryDto build() {
      return countryDto;
    }

  }

}
