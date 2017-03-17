package mapper.entity;

import dto.BloodGroupListDto;
import entity.BloodGroup;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nyomoto on 18.2.2017.
 */
public class BloodGroupEntityMapper {

  /**
   * BloodGroupListDto listesini BloodGroup entity listesine çevirir.
   * @param dtos BloodGroupListDto listesi
   * @return BloodGroup entity listesi
   */
  public static List<BloodGroup> mapDtosToEntities(List<BloodGroupListDto> dtos) {
    return dtos.stream().map(BloodGroupEntityMapper::mapDtoToEntity).collect(Collectors.toList());
  }

  /**
   * BloodGroupListDtosunu BloodGroup a çevirir.
   * @param dto BloodGroupListDto
   * @return BloodGroup entitysi
   */
  public static BloodGroup mapDtoToEntity(BloodGroupListDto dto) {
    return BloodGroup.getBuilder()
        .id(dto.getId())
        .name(dto.getName())
        .active(dto.getActive())
        .build();
  }
}
