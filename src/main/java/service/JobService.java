package service;

import dto.JobListDto;

import java.util.List;

/**
 * Created by Nyomoto on 11.2.2017.
 */
public interface JobService {

  List<JobListDto> getAll();

  JobListDto getById(int jobId) throws Exception;

  void deleteById(int jobId);

  int saveOrUpdate(JobListDto dto) throws Exception;

}
