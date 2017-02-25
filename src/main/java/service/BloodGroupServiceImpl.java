package service;

import dto.BloodGroupListDto;
import entity.BloodGroup;
import jpa.BloodGroupRepository;
import mapper.dto.BloodGroupListDtoMapper;
import mapper.entity.BloodGroupEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nyomoto on 18.2.2017.
 */
@Service
public class BloodGroupServiceImpl implements BloodGroupService {

    @Autowired
    BloodGroupRepository bloodGroupRepository;

    @Override
    public List<BloodGroupListDto> getAll() {
        List<BloodGroup> list = bloodGroupRepository.findAll();
        return BloodGroupListDtoMapper.mapEntitiesToDtos(list);
    }

    @Override
    public BloodGroupListDto getByID(int bloodGroupID) {
        BloodGroup entity = bloodGroupRepository.getOne(bloodGroupID);
        return BloodGroupListDtoMapper.mapEntityToDto(entity);
    }

    @Override
    public void deleteByID(int jobID) {
        bloodGroupRepository.delete(jobID);
    }

    @Override
    public int saveOrUpdate(BloodGroupListDto dto) {
        BloodGroup entity = BloodGroupEntityMapper.mapDtoToEntity(dto);
        entity = bloodGroupRepository.save(entity);

        return  entity.getId();
    }

}
