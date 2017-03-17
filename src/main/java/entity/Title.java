package entity;

import javax.persistence.*;
import java.util.Date;

/**
 * TITLE tablosu ile map edildi.
 * Bu entity bir ünvanın özelliklerini gösterir.
 * Created by Nyomoto on 11.2.2017.
 */
@Entity
@Table(name = "TITLE")
@SequenceGenerator(name = "ID_SEQ",
    sequenceName = "SEQ_TITLE_ID",
    allocationSize = 1)
public class Title {

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

  public static TitleBuilder getBuilder() {
    return new TitleBuilder();
  }

  public static class TitleBuilder {

    private Title entity;

    public TitleBuilder() {
      entity = new Title();
    }


    public TitleBuilder id(int id) {
      this.entity.setId(id);
      return this;
    }

    public TitleBuilder name(String name) {
      this.entity.setName(name);
      return this;
    }

    public TitleBuilder active(boolean active) {
      this.entity.setActive(active);
      return this;
    }

    public Title build() {
      return entity;
    }

  }
}
