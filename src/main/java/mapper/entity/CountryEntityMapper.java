package mapper.entity;

import dto.CountryDto;
import entity.Country;

/**
 * Created by Coşkun on 4.2.2017.
 */
public class CountryEntityMapper {

  //    public static List<Country> mapDtosToEntities(List<CountryListDto> dtos) {
  //        return dtos.stream().map(CountryEntityMapper::mapDtoToEntity).collect(Collectors.toList());
  //    }

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
