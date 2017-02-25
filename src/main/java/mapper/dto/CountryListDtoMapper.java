package mapper.dto;

import dto.CountryListDto;
import entity.Country;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Coşkun on 4.2.2017.
 */
public class CountryListDtoMapper {

    public static List<CountryListDto> mapEntitiesToDtos(List<Country> entities) {
        return entities.stream().map(CountryListDtoMapper::mapEntityToDto).collect(Collectors.toList());
    }

    public static CountryListDto mapEntityToDto(Country entity) {
        return CountryListDto.getBuilder()
                .id(entity.getId())
                .name(entity.getName())
                .active(entity.getActive())
                .build();
    }
}
