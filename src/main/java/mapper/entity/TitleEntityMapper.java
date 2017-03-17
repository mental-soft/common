package mapper.entity;

import dto.TitleListDto;
import entity.Title;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nyomoto on 18.2.2017.
 */
public class TitleEntityMapper {

  /**
   * TitleListDto listesini Title entity listesine çevirir.
   * @param dtos TitleListDto listesi
   * @return Title entity listesi
   */
  public static List<Title> mapDtosToEntities(List<TitleListDto> dtos) {
    return dtos.stream().map(TitleEntityMapper::mapDtoToEntity).collect(Collectors.toList());
  }

  /**
   * TitleListDtosunu Title a çevirir.
   * @param dto TitleListDto
   * @return Title entitysi
   */
  public static Title mapDtoToEntity(TitleListDto dto) {
    return Title.getBuilder()
        .id(dto.getId())
        .name(dto.getName())
        .active(dto.getActive())
        .build();
  }
}
