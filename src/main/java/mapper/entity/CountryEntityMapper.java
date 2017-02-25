package mapper.entity;

import dto.CountryListDto;
import entity.Country;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Coşkun on 4.2.2017.
 */
public class CountryEntityMapper {

    public static List<Country> mapDtosToEntities(List<CountryListDto> dtos) {
        return dtos.stream().map(CountryEntityMapper::mapDtoToEntity).collect(Collectors.toList());
    }

    public static Country mapDtoToEntity(CountryListDto dto) {
        return Country.getBuilder()
                .id(dto.getId())
                .name(dto.getName())
                .active(dto.getActive())
                .build();
    }

}
