package mapper.dto;

import dto.CountryDto;
import entity.Country;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Coşkun on 4.2.2017.
 */
public class CountryListDtoMapper {

  /**
   * Country entity listesini CountryDto listesine çevirir.
   * @param entities Country entity listesi
   * @return CountryDto listesi
   */
  public static List<CountryDto> mapEntitiesToDtos(List<Country> entities) {
    return entities.stream().map(CountryListDtoMapper::mapEntityToDto).collect(Collectors.toList());
  }

  /**
   * Country entity sini CountryDto ya çevirir.
   * @param entity Country entitysi
   * @return CountryDto
   */
  public static CountryDto mapEntityToDto(Country entity) {
    return CountryDto.getBuilder()
        .id(entity.getId())
        .name(entity.getName())
        .code(entity.getCode())
        .enName(entity.getEnName())
        .active(entity.getActive())
        .modifiedDate(entity.getModifiedDate())
        .createdDate(entity.getCreatedDate())
        .build();
  }

}
