package service;

import dto.JobListDto;
import entity.Job;
import jpa.JobRepository;
import mapper.dto.JobListDtoMapper;
import mapper.entity.JobEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nyomoto on 11.2.2017.
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;

    @Override
    public long jobCount() {
        return jobRepository.count();
    }

    @Override
    public List<JobListDto> getAll() {
        List<Job> list = jobRepository.findAll();
        return JobListDtoMapper.mapEntitiesToDtos(list);
    }

    @Override
    public JobListDto getByID(int jobID) {
        Job entity = jobRepository.getOne(jobID);
        return JobListDtoMapper.mapEntityToDto(entity);
    }

    @Override
    public void deleteByID(int jobID) {
        jobRepository.delete(jobID);
    }

    @Override
    public void saveOrUpdate(JobListDto dto) {
        Job entity = JobEntityMapper.mapDtoToEntity(dto);
        jobRepository.save(entity);
    }

}
