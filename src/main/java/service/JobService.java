package service;

import dto.JobListDto;

import java.util.List;

/**
 * Created by Nyomoto on 11.2.2017.
 */
public interface JobService {
    long jobCount();

    List<JobListDto> getAll();

    JobListDto getByID(int jobID);

    void deleteByID(int jobID);

    void saveOrUpdate(JobListDto dto);
}
