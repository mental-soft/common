package mapper.dto;

import dto.DistrictDto;
import entity.District;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by okan on 15.02.2017.
 */
public class DistrictListDtoMapper {
  /**
   * District entity listesini DistrictDto listesine çevirir.
   *
   * @param entities District entity listesi
   * @return DistrictDto listesi
   */
  public static List<DistrictDto> mapEntitiesToDtos(List<District> entities) {
    return entities.stream()
        .map(DistrictListDtoMapper::mapEntityToDto)
        .collect(Collectors.toList());
  }

  /**
   * District entity sini DistrictDto ya çevirir.
   *
   * @param entity District entitysi
   * @return DistrictDto
   */

  public static DistrictDto mapEntityToDto(District entity) {
    return DistrictDto.getBuilder()
        .id(entity.getId())
        .cityDto(CityListDtoMapper.mapEntityToDto(entity.getCity()))
        .name(entity.getName())
        .active(entity.getActive())
        .modifiedDate(entity.getModifiedDate())
        .createdDate(entity.getCreatedDate())
        .build();
  }

}