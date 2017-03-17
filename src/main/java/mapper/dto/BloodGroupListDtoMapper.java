package mapper.dto;

import dto.BloodGroupListDto;
import entity.BloodGroup;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nyomoto on 18.2.2017.
 */
public class BloodGroupListDtoMapper {

  /**
   * BloodGroup entity listesini BloodGroupListDto listesine çevirir.
   * @param entities BloodGroup entity listesi
   * @return BloodGroupListDto listesi
   */
  public static List<BloodGroupListDto> mapEntitiesToDtos(List<BloodGroup> entities) {
    return entities.stream()
        .map(BloodGroupListDtoMapper::mapEntityToDto)
        .collect(Collectors.toList());
  }

  /**
   * BloodGroup entity sini BloodGroupListDto ya çevirir.
   * @param entity BloodGroup entitysi
   * @return BloodGroupListDto
   */
  public static BloodGroupListDto mapEntityToDto(BloodGroup entity) {
    return BloodGroupListDto.getBuilder()
        .id(entity.getId())
        .name(entity.getName())
        .active(entity.getActive())
        .build();
  }

}
