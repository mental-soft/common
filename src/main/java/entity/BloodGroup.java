package entity;

import javax.persistence.*;
import java.util.Date;

/**
 * BLOOD_GROUP tablosu ile map edildi.
 * Bu entity bir kan grubunun özelliklerini gösterir.
 * Created by Nyomoto on 11.2.2017.
 */
@Entity
@Table(name = "BLOOD_GROUP")
@SequenceGenerator(name = "ID_SEQ",
    sequenceName = "SEQ_BLOOD_GROUP_ID",
    allocationSize = 1)
public class BloodGroup {

  @Id
  @Column(name = "ID")
  private Integer id;

  @Column(name = "NAME", columnDefinition = "NVARCHAR2")
  private String name;

  @Column(name = "IS_ACTIVE", columnDefinition = "NUMBER")
  private Boolean isActive;

  @Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP")
  private Date createdDate;

  @Column(name = "MODIFIED_DATE", columnDefinition = "TIMESTAMP")
  private Date modifiedDate;

  @PreUpdate
  public void preUpdate() {
    modifiedDate = new Date();
  }

  @PrePersist
  public void prePersist() {
    createdDate = new Date();
  }

  public Integer getId() {
    return id;
  }

  public void setId(int id) {
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

  public static BloodGroupBuilder getBuilder() {
    return new BloodGroupBuilder();
  }

  public static class BloodGroupBuilder {

    private BloodGroup entity;

    public BloodGroupBuilder() {
      entity = new BloodGroup();
    }


    public BloodGroupBuilder id(int id) {
      this.entity.setId(id);
      return this;
    }

    public BloodGroupBuilder name(String name) {
      this.entity.setName(name);
      return this;
    }

    public BloodGroupBuilder active(boolean active) {
      this.entity.setActive(active);
      return this;
    }

    public BloodGroup build() {
      return entity;
    }

  }

}
