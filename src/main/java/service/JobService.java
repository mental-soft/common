package service;

import dto.JobListDto;

import java.util.List;

/**
 * Created by Nyomoto on 11.2.2017.
 */
public interface JobService {

    List<JobListDto> getAll();

    JobListDto getByID(int jobID) throws Exception;

    void deleteByID(int jobID);

    int saveOrUpdate(JobListDto dto) throws Exception;

}
