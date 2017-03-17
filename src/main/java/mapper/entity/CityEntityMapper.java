package mapper.entity;

import dto.CityDto;
import entity.City;


/**
 * Created by okan on 12.02.2017.
 */
public class CityEntityMapper {

  public static City mapDtoToEntity(CityDto cityDto) {

    return City.getBuilder()
        .id(cityDto.getId())
        .name(cityDto.getName())
        .code(cityDto.getCode())
        .big(cityDto.getBig())
        .active(cityDto.getActive())
        .modifiedDate(cityDto.getModifiedDate())
        .createdDate(cityDto.getCreatedDate())
        .country(CountryEntityMapper.mapDtoToEntity(cityDto.getCountryDto()))
        .build();

  }
}


