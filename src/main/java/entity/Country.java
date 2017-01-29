package entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "country")
    private List<City> cities;

    public Integer getId() {
        return id;
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

}
