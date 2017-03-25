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
 * District tablosu ile map edildi.
 * Bu entity bir ilçenin özelliklerini gösterir.
 * Created by Coşkun on 21.1.2017.
 */
@Entity
@Table(name = "DISTRICT")
@SequenceGenerator(name = "ID_SEQ",
    sequenceName = "SEQ_DISTRICT_ID",
    allocationSize = 1)
public class District {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ")
  @Column(name = "ID")
  private Integer id;

  @Column(name = "NAME", columnDefinition = "NVARCHAR2")
  private String name;

  @Column(name = "IS_ACTIVE", columnDefinition = "NUMBER")
  private Boolean isActive;

  @Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP")
  private LocalDateTime createdDate;

  @Column(name = "MODIFIED_DATE", columnDefinition = "TIMESTAMP")
  private LocalDateTime modifiedDate;

  @ManyToOne
  @JoinColumn(name = "CITY_ID", referencedColumnName = "ID")
  private City city;

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

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public static DistrictBuilder getBuilder() {
    return new DistrictBuilder();
  }

  public static class DistrictBuilder {

    private District district;

    public DistrictBuilder() {
      district = new District();
    }

    public DistrictBuilder id(Integer id) {
      this.district.setId(id);
      return this;
    }

    public DistrictBuilder name(String name) {
      this.district.setName(name);
      return this;
    }

    public DistrictBuilder modifiedDate(LocalDateTime modifiedDate) {
      this.district.setModifiedDate(modifiedDate);
      return this;
    }

    public DistrictBuilder createdDate(LocalDateTime createdDate) {
      this.district.setCreatedDate(createdDate);
      return this;
    }

    public DistrictBuilder active(Boolean active) {
      this.district.setActive(active);
      return this;
    }

    public DistrictBuilder city(City city) {
      this.district.setCity(city);
      return this;
    }

    public District build() {
      return district;
    }

  }

}
