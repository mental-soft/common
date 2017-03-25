package mapper.dto;

import dto.JobListDto;
import entity.Job;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nyomoto on 11.2.2017.
 */
public class JobListDtoMapper {

  /**
   * Job entity listesini JobListDto listesine çevirir.
   * @param entities Job entity listesi
   * @return JobListDto listesi
   */
  public static List<JobListDto> mapEntitiesToDtos(List<Job> entities) {
    return entities.stream().map(JobListDtoMapper::mapEntityToDto).collect(Collectors.toList());
  }

  /**
   * Job entity sini JobListDto ya çevirir.
   * @param entity Job entitysi
   * @return JobListDto
   */
  public static JobListDto mapEntityToDto(Job entity) {
    return JobListDto.getBuilder()
        .id(entity.getId())
        .name(entity.getName())
        .active(entity.getActive())
        .build();
  }

}
