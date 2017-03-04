package mapper;

import dto.CountryDto;
import entity.Country;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by okan on 12.02.2017.
 */
public class DtoToCountryMapper {

    public static Country mapDtoToEntity(CountryDto countryDto) {

            return Country.getBuilder()
                    .id(countryDto.getId())
                    .name(countryDto.getName())
                    .code(countryDto.getCode())
                    .enName(countryDto.getEnName())
                    .active(countryDto.getActive())
                    .modifiedDate(countryDto.getModifiedDate())
                    .createdDate(countryDto.getCreatedDate())
                    .build();
        }

    }


