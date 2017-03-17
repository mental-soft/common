package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Country tablosu ile map edildi.
 * Bu entity bir ülkenin özelliklerini gösterir.
 * Created by Coşkun on 21.1.2017.
 */
@Entity
@Table(name = "COUNTRY")
@SequenceGenerator(name = "ID_SEQ",
    sequenceName = "SEQ_COUNTRY_ID",
    allocationSize = 1)
public class Country {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ")
  @Column(name = "ID")
  private Integer id;

  @Column(name = "NAME", columnDefinition = "NVARCHAR2")
  private String name;

  @Column(name = "CODE", columnDefinition = "NVARCHAR2")
  private String code;

  @Column(name = "EN_NAME", columnDefinition = "NVARCHAR2")
  private String enName;

  @Column(name = "IS_ACTIVE", columnDefinition = "NUMBER")
  private Boolean isActive;

  @Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP")
  private Date createdDate;

  @Column(name = "MODIFIED_DATE", columnDefinition = "TIMESTAMP")
  private Date modifiedDate;

  @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
  private List<City> cities;

  @PreUpdate
  public void preUpdate() {
    modifiedDate = new Date();
  }

  @PrePersist
  public void prePersist() {
    createdDate = new Date();
  }

  public Country() {
    cities = new ArrayList<>();
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

  public String getEnName() {
    return enName;
  }

  public void setEnName(String enName) {
    this.enName = enName;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(Date modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public Boolean getActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public List<City> getCities() {
    return cities;
  }

  public void setCities(List<City> cities) {
    this.cities = cities;
  }


  public static CountryBuilder getBuilder() {
    return new CountryBuilder();

  }

  public static class CountryBuilder {


    private Country country;

    public CountryBuilder() {
      country = new Country();
    }

    public CountryBuilder id(Integer id) {
      this.country.setId(id);
      return this;
    }

    public CountryBuilder name(String name) {
      this.country.setName(name);
      return this;
    }

    public CountryBuilder enName(String enName) {
      this.country.setEnName(enName);
      return this;
    }

    public CountryBuilder code(String code) {
      this.country.setCode(code);
      return this;
    }

    public CountryBuilder modifiedDate(Date modifiedDate) {
      this.country.setModifiedDate(modifiedDate);
      return this;
    }

    public CountryBuilder createdDate(Date createdDate) {
      this.country.setCreatedDate(createdDate);
      return this;
    }

    public CountryBuilder active(Boolean active) {
      this.country.setActive(active);
      return this;
    }

    public Country build() {
      return country;

    }

  }

}
