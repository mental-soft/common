package mapper;



import dto.DistrictDto;
import entity.District;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by okan on 15.02.2017.
 */
public class DistrictListDtoMapper {
    public static List<DistrictDto> mapEntitiesToDtos(List<District> entities) {
        return entities.stream().map(DistrictListDtoMapper::mapEntityToDto).collect(Collectors.toList());
    }

    public static DistrictDto mapEntityToDto(District entity) {
        return DistrictDto.getBuilder()
                .id(entity.getId())
                .city(entity.getCity())
                .name(entity.getName())
                .active(entity.getActive())
                .modifiedDate(entity.getModifiedDate())
                .createdDate(entity.getCreatedDate())
                .build();
    }

}