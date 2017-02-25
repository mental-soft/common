package dto;

/**
 * Bu sınıf ünvan listesinde kullanılacak.
 * Ünvanın idsini, adını ve aktif olup olmadığını gösteren bilgileri içerir.
 * Created by Nyomoto on 18.2.2017.
 */
public class TitleListDto {

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

    public static TitleListDtoBuilder getBuilder() {
        return new TitleListDtoBuilder();
    }

    public static class TitleListDtoBuilder {

        private TitleListDto titleListDto;

        public TitleListDtoBuilder() {
            titleListDto = new TitleListDto();
        }


        public TitleListDtoBuilder id(int id) {
            this.titleListDto.setId(id);
            return this;
        }

        public TitleListDtoBuilder name(String name) {
            this.titleListDto.setName(name);
            return this;
        }

        public TitleListDtoBuilder active(boolean active) {
            this.titleListDto.setActive(active);
            return this;
        }

        public TitleListDto build() {
            return titleListDto;
        }

    }

}
