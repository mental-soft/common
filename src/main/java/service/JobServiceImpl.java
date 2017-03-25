package service;

import dto.JobListDto;
import entity.Job;

import java.util.List;

import jpa.JobRepository;
import mapper.dto.JobListDtoMapper;
import mapper.entity.JobEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nyomoto on 11.2.2017.
 */
@Service
public class JobServiceImpl implements JobService {

  //region Messages
  public static final String NOT_FOUND_MESSAGE = "Meslek kaydı bulunamadı.";
  public static final String PARAMETERS_MUST_BE_NOT_NULL = "Parametre girilmesi gerekmektedir.";
  public static final String JOB_NAME_MUST_BE_NOT_NULL = "Meslek Adı girilmesi gerekmektedir.";
  //endregion

  @Autowired
  JobRepository jobRepository;

  @Override
  public List<JobListDto> getAll() {
    List<Job> list = jobRepository.findAll();
    return JobListDtoMapper.mapEntitiesToDtos(list);
  }

  @Override
  public JobListDto getById(int jobId) throws Exception {
    Job entity = jobRepository.getOne(jobId);

    if (entity == null) {
      throw new Exception(NOT_FOUND_MESSAGE);
    }

    return JobListDtoMapper.mapEntityToDto(entity);
  }

  @Override
  public void deleteById(int jobId) {
    jobRepository.delete(jobId);
  }

  @Override
  public int saveOrUpdate(JobListDto dto) throws Exception {
    if (dto == null) {
      throw new Exception(PARAMETERS_MUST_BE_NOT_NULL);
    }

    if (dto.getName() == null || dto.getName().isEmpty()) {
      throw new Exception(JOB_NAME_MUST_BE_NOT_NULL);
    }

    Job entity = JobEntityMapper.mapDtoToEntity(dto);
    entity = jobRepository.save(entity);

    return entity.getId();
  }

}
