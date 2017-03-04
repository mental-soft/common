package mapper.entity;

import dto.JobListDto;
import entity.Job;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nyomoto on 11.2.2017.
 */
public class JobEntityMapper {

    public static List<JobListDto> mapEntitiesToDtos(List<Job> entities) {
        return entities.stream().map(JobEntityMapper::mapEntityToDto).collect(Collectors.toList());
    }

    public static JobListDto mapEntityToDto(Job entity) {
        return JobListDto.getBuilder()
                .id(entity.getId())
                .name(entity.getName())
                .active(entity.getActive())
                .build();
    }

    public static List<Job> mapDtosToEntities(List<JobListDto> dtos) {
        return dtos.stream().map(JobEntityMapper::mapDtoToEntity).collect(Collectors.toList());
    }

    public static Job mapDtoToEntity(JobListDto dto) {
        return Job.getBuilder()
                .id(dto.getId())
                .name(dto.getName())
                .active(dto.getActive())
                .build();
    }
}
