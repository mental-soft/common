package mapper.dto;

import dto.TitleListDto;
import entity.Title;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nyomoto on 18.2.2017.
 */
public class TitleListDtoMapper {

    public static List<TitleListDto> mapEntitiesToDtos(List<Title> entities) {
        return entities.stream().map(TitleListDtoMapper::mapEntityToDto).collect(Collectors.toList());
    }

    public static TitleListDto mapEntityToDto(Title entity) {
        return TitleListDto.getBuilder()
                .id(entity.getId())
                .name(entity.getName())
                .active(entity.getActive())
                .build();
    }

}
