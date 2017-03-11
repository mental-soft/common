package mapper.entity;

import dto.DistrictDto;
import entity.City;
import entity.District;

/**
 * Created by okan on 15.02.2017.
 */
public class DistrictEntityMapper {

    public static District mapDtoToEntity(DistrictDto districtDto) {

        return District.getBuilder()
                .id(districtDto.getId())
                .name(districtDto.getName())
                .active(districtDto.getActive())
                .modifiedDate(districtDto.getModifiedDate())
                .createdDate(districtDto.getCreatedDate())
                .city( CityEntityMapper.mapDtoToEntity(districtDto.getCityDto()))
                .build();

    }
}