package entity;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Job tablosu ile map edildi.
 * Bu entity bir mesleğin özelliklerini gösterir.
 * Created by Coşkun on 21.1.2017.
 */
@Entity
@Table(name = "JOB")
@SequenceGenerator(name = "ID_SEQ",
    sequenceName = "SEQ_JOB_ID",
    allocationSize = 1)
public class Job {

  @Id
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

  public static JobBuilder getBuilder() {
    return new JobBuilder();
  }

  public static class JobBuilder {

    private Job entity;

    public JobBuilder() {
      entity = new Job();
    }


    public JobBuilder id(int id) {
      this.entity.setId(id);
      return this;
    }

    public JobBuilder name(String name) {
      this.entity.setName(name);
      return this;
    }

    public JobBuilder active(boolean active) {
      this.entity.setActive(active);
      return this;
    }

    public Job build() {
      return entity;
    }

  }
}
