package entity;

import dto.CountryDto;
import mapper.entity.CityEntityMapper;
import mapper.entity.CountryEntityMapper;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * City tablosu ile map edildi.
 * Bu entity bir şehirin özelliklerini gösterir.
 * Created by Coşkun on 21.1.2017.
 */
@Entity
@Table(name = "CITY")
@SequenceGenerator(name = "ID_SEQ",
        sequenceName = "SEQ_CITY_ID",
        allocationSize = 1)
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ")
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
    private Date createdDate;

    @Column(name = "MODIFIED_DATE", columnDefinition = "TIMESTAMP")
    private Date modifiedDate;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID", referencedColumnName = "ID")
    private Country country;

    @PreUpdate
    public void preUpdate(){
        modifiedDate = new Date();
    }

    @PrePersist
    public void prePersist(){
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

        public CityBuilder modifiedDate(Date modifiedDate) {
            this.city.setModifiedDate(modifiedDate);
            return this;
        }

        public CityBuilder createdDate(Date createdDate) {
            this.city.setCreatedDate(createdDate);
            return this;
        }

        public CityBuilder active(Boolean active) {
            this.city.setActive(active);
            return this;
        }

        public CityBuilder country(Country country) {
            //this.city.setCountry(CountryEntityMapper.mapDtoToEntity(countryDto));
            this.city.setCountry(country);
            return this;
        }
        public City build() {
            return city;
        }

    }

}
