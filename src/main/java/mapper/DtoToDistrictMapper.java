package mapper;

import dto.DistrictDto;
import entity.District;

/**
 * Created by okan on 15.02.2017.
 */
public class DtoToDistrictMapper {

    public static District mapDtoToEntity(DistrictDto districtDto) {

        return District.getBuilder()
                .id(districtDto.getId())
                .name(districtDto.getName())
                .active(districtDto.getActive())
                .modifiedDate(districtDto.getModifiedDate())
                .createdDate(districtDto.getCreatedDate())
                .city((districtDto.getCity()))
                .build();

    }
}