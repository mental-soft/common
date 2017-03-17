package mapper.dto;

import dto.CountryDto;
import entity.Country;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Coşkun on 4.2.2017.
 */
public class CountryListDtoMapper {

  public static List<CountryDto> mapEntitiesToDtos(List<Country> entities) {
    return entities.stream().map(CountryListDtoMapper::mapEntityToDto).collect(Collectors.toList());
  }

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
