package mapper.dto;

import dto.TitleListDto;
import entity.Title;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nyomoto on 18.2.2017.
 */
public class TitleListDtoMapper {

  /**
   * Title entity listesini TitleListDto listesine çevirir.
   * @param entities Title entity listesi
   * @return TitleListDto listesi
   */
  public static List<TitleListDto> mapEntitiesToDtos(List<Title> entities) {
    return entities.stream().map(TitleListDtoMapper::mapEntityToDto).collect(Collectors.toList());
  }

  /**
   * Title entity sini TitleListDto ya çevirir.
   * @param entity Title entitysi
   * @return TitleListDto
   */
  public static TitleListDto mapEntityToDto(Title entity) {
    return TitleListDto.getBuilder()
        .id(entity.getId())
        .name(entity.getName())
        .active(entity.getActive())
        .build();
  }

}
