package dto;

/**
 * Bu sınıf meslek listesinde kullanılacak.
 * Mesleğin idsini, adını ve aktif olup olmadığını gösteren bilgileri içerir.
 * Created by Nyomoto on 18.2.2017.
 */
public class JobListDto {

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

  public static JobListDtoBuilder getBuilder() {
    return new JobListDtoBuilder();
  }

  public static class JobListDtoBuilder {

    private JobListDto jobListDto;

    public JobListDtoBuilder() {
      jobListDto = new JobListDto();
    }


    public JobListDtoBuilder id(int id) {
      this.jobListDto.setId(id);
      return this;
    }

    public JobListDtoBuilder name(String name) {
      this.jobListDto.setName(name);
      return this;
    }

    public JobListDtoBuilder active(boolean active) {
      this.jobListDto.setActive(active);
      return this;
    }

    public JobListDto build() {
      return jobListDto;
    }

  }

}
