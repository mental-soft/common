package entity;

import javax.persistence.*;
import java.util.Date;

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
    private Date createdDate;

    @Column(name = "MODIFIED_DATE", columnDefinition = "TIMESTAMP")
    private Date modifiedDate;

    public Integer getId() {
        return id;
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

}
