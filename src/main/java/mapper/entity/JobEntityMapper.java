package mapper.entity;

import dto.JobListDto;
import entity.Job;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nyomoto on 11.2.2017.
 */
public class JobEntityMapper {

  /**
   * JobListDto listesini Job entity listesine çevirir.
   * @param dtos JobListDto listesi
   * @return Job entity listesi
   */
  public static List<Job> mapDtosToEntities(List<JobListDto> dtos) {
    return dtos.stream().map(JobEntityMapper::mapDtoToEntity).collect(Collectors.toList());
  }

  /**
   * JobListDtosunu Job a çevirir.
   * @param dto JobListDto
   * @return Job entitysi
   */
  public static Job mapDtoToEntity(JobListDto dto) {
    return Job.getBuilder()
        .id(dto.getId())
        .name(dto.getName())
        .active(dto.getActive())
        .build();
  }
}
