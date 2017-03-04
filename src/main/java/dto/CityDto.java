package dto;

import entity.Country;

import java.util.Date;

/**
 * Created by okan on 12.02.2017.
 */
public class CityDto {

    private Integer id;
    private String name;
    private String code;
    private Boolean isBig;
    private Boolean isActive;
    private Date modifiedDate;
    private Date createdDate;
    private Country country;

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

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public static CityDtoBuilder getBuilder() {
        return new CityDtoBuilder();
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public static class CityDtoBuilder {

        private CityDto cityDto;

        public CityDtoBuilder() {
            cityDto = new CityDto();
        }

        public CityDtoBuilder id(Integer id) {
            this.cityDto.setId(id);
            return this;
        }

        public CityDtoBuilder code(String code) {
            this.cityDto.setCode(code);
            return this;
        }

        public CityDtoBuilder name(String name) {
            this.cityDto.setName(name);
            return this;
        }

        public CityDtoBuilder modifiedDate(Date modifiedDate) {
            this.cityDto.setModifiedDate(modifiedDate);
            return this;
        }

        public CityDtoBuilder createdDate(Date createdDate) {
            this.cityDto.setCreatedDate(createdDate);
            return this;
        }

        public CityDtoBuilder active(Boolean active) {
            this.cityDto.setActive(active);
            return this;
        }

        public CityDtoBuilder big(Boolean big) {
            this.cityDto.setBig(big);
            return this;
        }

        public CityDtoBuilder country(Country country) {
            this.cityDto.setCountry(country);
            return this;
        }

        public CityDto build() {
            return cityDto;
        }
    }

}
