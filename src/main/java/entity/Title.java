package entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
