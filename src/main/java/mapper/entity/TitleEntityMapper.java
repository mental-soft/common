package mapper.entity;

import dto.TitleListDto;
import entity.Title;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nyomoto on 18.2.2017.
 */
public class TitleEntityMapper {

  public static List<TitleListDto> mapEntitiesToDtos(List<Title> entities) {
    return entities.stream().map(TitleEntityMapper::mapEntityToDto).collect(Collectors.toList());
  }

  public static TitleListDto mapEntityToDto(Title entity) {
    return TitleListDto.getBuilder()
        .id(entity.getId())
        .name(entity.getName())
        .active(entity.getActive())
        .build();
  }

  public static List<Title> mapDtosToEntities(List<TitleListDto> dtos) {
    return dtos.stream().map(TitleEntityMapper::mapDtoToEntity).collect(Collectors.toList());
  }

  public static Title mapDtoToEntity(TitleListDto dto) {
    return Title.getBuilder()
        .id(dto.getId())
        .name(dto.getName())
        .active(dto.getActive())
        .build();
  }
}
