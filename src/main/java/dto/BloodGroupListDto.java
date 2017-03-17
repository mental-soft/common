package dto;

/**
 * Bu sınıf Kan grubu listesinde kullanılacak.
 * Kan grubunun idsini, adını ve aktif olup olmadığını gösteren bilgileri içerir.
 * Created by Nyomoto on 18.2.2017.
 */
public class BloodGroupListDto {

  private int id;
  private String name;
  private boolean isActive;

  public int getId() {
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

  public boolean getActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public static BloodGroupListDtoBuilder getBuilder() {
    return new BloodGroupListDtoBuilder();
  }

  public static class BloodGroupListDtoBuilder {

    private BloodGroupListDto bloodGroupListDto;

    public BloodGroupListDtoBuilder() {
      bloodGroupListDto = new BloodGroupListDto();
    }


    public BloodGroupListDtoBuilder id(int id) {
      this.bloodGroupListDto.setId(id);
      return this;
    }

    public BloodGroupListDtoBuilder name(String name) {
      this.bloodGroupListDto.setName(name);
      return this;
    }

    public BloodGroupListDtoBuilder active(boolean active) {
      this.bloodGroupListDto.setActive(active);
      return this;
    }

    public BloodGroupListDto build() {
      return bloodGroupListDto;
    }

  }

}
