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

    public static final String NOT_FOUND_MESSAGE = "Meslek kaydı bulunamadı.";

    @Autowired
    JobRepository jobRepository;

    @Override
    public List<JobListDto> getAll() {
        List<Job> list = jobRepository.findAll();
        return JobListDtoMapper.mapEntitiesToDtos(list);
    }

    @Override
    public JobListDto getByID(int jobID) throws Exception {
        Job entity = jobRepository.getOne(jobID);

        if(entity == null) {
            throw new Exception(NOT_FOUND_MESSAGE);
        }

        return JobListDtoMapper.mapEntityToDto(entity);
    }

    @Override
    public void deleteByID(int jobID) {
        jobRepository.delete(jobID);
    }

    @Override
    public int saveOrUpdate(JobListDto dto) {
        Job entity = JobEntityMapper.mapDtoToEntity(dto);
        entity = jobRepository.save(entity);

        return entity.getId();
    }

}
