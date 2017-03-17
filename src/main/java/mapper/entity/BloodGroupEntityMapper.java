package mapper.entity;

import dto.BloodGroupListDto;
import entity.BloodGroup;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nyomoto on 18.2.2017.
 */
public class BloodGroupEntityMapper {

  public static List<BloodGroup> mapDtosToEntities(List<BloodGroupListDto> dtos) {
    return dtos.stream().map(BloodGroupEntityMapper::mapDtoToEntity).collect(Collectors.toList());
  }

  public static BloodGroup mapDtoToEntity(BloodGroupListDto dto) {
    return BloodGroup.getBuilder()
        .id(dto.getId())
        .name(dto.getName())
        .active(dto.getActive())
        .build();
  }
}
