package mapper.dto;

import dto.CityDto;
import dto.CountryDto;
import entity.City;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Okan on 14.2.2017.
 */
public class CityListDtoMapper {

    public static List<CityDto> mapEntitiesToDtos(List<City> entities) {
        return entities.stream().map(CityListDtoMapper::mapEntityToDto).collect(Collectors.toList());
    }

    public static CityDto mapEntityToDto(City entity) {
        return CityDto.getBuilder()
                .id(entity.getId())
                .countryDto(CountryListDtoMapper.mapEntityToDto(entity.getCountry()))
                .name(entity.getName())
                .code(entity.getCode())
                .big((entity.getBig()))
                .active(entity.getActive())
                .modifiedDate(entity.getModifiedDate())
                .createdDate(entity.getCreatedDate())
                .build();
    }

}
