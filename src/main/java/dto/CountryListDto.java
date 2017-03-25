package dto;

/**
 * Bu sınıf ülke listesinde kullanılacak.
 * Ülkenin idsini, adını ve ingilizce adını,
 * ülke kodunu ve aktif olup olmadığını sağlar.
 * Created by Coşkun on 4.2.2017.
 */
public class CountryListDto {

  private int id;
  private String name;
  private String enName;
  private String code;
  private boolean isActive;

  public int getId() {
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

  public String getEnName() {
    return enName;
  }

  public void setEnName(String enName) {
    this.enName = enName;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public boolean getActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public static CountryListDtoBuilder getBuilder() {
    return new CountryListDtoBuilder();
  }

  public static class CountryListDtoBuilder {

    private CountryListDto countryListDto;

    public CountryListDtoBuilder() {
      countryListDto = new CountryListDto();
    }


    public CountryListDtoBuilder id(int id) {
      this.countryListDto.setId(id);
      return this;
    }

    public CountryListDtoBuilder name(String name) {
      this.countryListDto.setName(name);
      return this;
    }

    public CountryListDtoBuilder enName(String enName) {
      this.countryListDto.setEnName(enName);
      return this;
    }

    public CountryListDtoBuilder code(String code) {
      this.countryListDto.setCode(code);
      return this;
    }

    public CountryListDtoBuilder active(boolean active) {
      this.countryListDto.setActive(active);
      return this;
    }

    public CountryListDto build() {
      return countryListDto;
    }

  }

}
