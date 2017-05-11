package entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * City tablosu ile map edildi.
 * Bu entity bir şehirin özelliklerini gösterir.
 * Created by Coşkun on 21.1.2017.
 */
@Entity
@Table(name = "CITY")
//@SequenceGenerator(name = "ID_SEQ",
//    sequenceName = "SEQ_CITY_ID",
//    allocationSize = 1)
public class City {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "NAME", columnDefinition = "NVARCHAR2")
  private String name;

  @Column(name = "CODE", columnDefinition = "NVARCHAR2")
  private String code;

  @Column(name = "IS_BIG", columnDefinition = "NUMBER")
  private Boolean isBig;

  @Column(name = "IS_ACTIVE", columnDefinition = "NUMBER")
  private Boolean isActive;

  @Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP")
  private LocalDateTime createdDate;

  @Column(name = "MODIFIED_DATE", columnDefinition = "TIMESTAMP")
  private LocalDateTime modifiedDate;

  @ManyToOne
  @JoinColumn(name = "COUNTRY_ID", referencedColumnName = "ID")
  private Country country;

  @PreUpdate
  public void preUpdate() {
    modifiedDate = LocalDateTime.now();
  }

  @PrePersist
  public void prePersist() {
    createdDate = LocalDateTime.now();
  }

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

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Boolean getBig() {
    return isBig;
  }

  public void setBig(Boolean big) {
    isBig = big;
  }

  public Boolean getActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public LocalDateTime getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(LocalDateTime modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public static CityBuilder getBuilder() {
    return new CityBuilder();
  }

  public static class CityBuilder {

    private City city;

    public CityBuilder() {
      city = new City();
    }

    public CityBuilder id(Integer id) {
      this.city.setId(id);
      return this;
    }

    public CityBuilder name(String name) {
      this.city.setName(name);
      return this;
    }

    public CityBuilder big(Boolean big) {
      this.city.setBig(big);
      return this;
    }

    public CityBuilder code(String code) {
      this.city.setCode(code);
      return this;
    }

    public CityBuilder modifiedDate(LocalDateTime modifiedDate) {
      this.city.setModifiedDate(modifiedDate);
      return this;
    }

    public CityBuilder createdDate(LocalDateTime createdDate) {
      this.city.setCreatedDate(createdDate);
      return this;
    }

    public CityBuilder active(Boolean active) {
      this.city.setActive(active);
      return this;
    }

    public CityBuilder country(Country country) {
      this.city.setCountry(country);
      return this;
    }

    public City build() {
      return city;
    }

  }

}
