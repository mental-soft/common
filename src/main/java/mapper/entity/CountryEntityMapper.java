package mapper.entity;

import dto.CountryDto;
import entity.Country;

/**
 * Created by Coşkun on 4.2.2017.
 */
public class CountryEntityMapper {

  /**
   * CountryDtosunu Country e çevirir.
   * @param countryDto CountryDto
   * @return Country entitysi
   */
  public static Country mapDtoToEntity(CountryDto countryDto) {
    return Country.getBuilder()
        .id(countryDto.getId())
        .name(countryDto.getName())
        .code(countryDto.getCode())
        .enName(countryDto.getEnName())
        .active(countryDto.getIsActive())
        .modifiedDate(countryDto.getModifiedDate())
        .createdDate(countryDto.getCreatedDate())
        .build();
  }

}
