package service;

import dto.BloodGroupListDto;
import entity.BloodGroup;

import java.util.List;

import jpa.BloodGroupRepository;
import mapper.dto.BloodGroupListDtoMapper;
import mapper.entity.BloodGroupEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nyomoto on 18.2.2017.
 */
@Service
public class BloodGroupServiceImpl implements BloodGroupService {

  //region Messages
  public static final String NOT_FOUND_MESSAGE = "Kan grubu kaydı bulunamadı.";
  public static final String PARAMETERS_MUST_BE_NOT_NULL = "Parametre girilmesi gerekmektedir.";
  public static final String BLOODGROUP_NAME_MUST_BE_NOT_NULL = "Kan grubu adı "
      + "girilmesi gerekmektedir.";
  //endregion

  @Autowired
  BloodGroupRepository bloodGroupRepository;

  @Override
  public List<BloodGroupListDto> getAll() {
    List<BloodGroup> list = bloodGroupRepository.findAll();
    return BloodGroupListDtoMapper.mapEntitiesToDtos(list);
  }

  @Override
  public BloodGroupListDto getById(int bloodGroupId) throws Exception {
    BloodGroup entity = bloodGroupRepository.getOne(bloodGroupId);

    if (entity == null) {
      throw new Exception(NOT_FOUND_MESSAGE);
    }

    return BloodGroupListDtoMapper.mapEntityToDto(entity);
  }

  @Override
  public void deleteById(int jobId) {
    bloodGroupRepository.delete(jobId);
  }

  @Override
  public int saveOrUpdate(BloodGroupListDto dto) throws Exception {
    if (dto == null) {
      throw new Exception(PARAMETERS_MUST_BE_NOT_NULL);
    }

    if (dto.getName() == null || dto.getName().isEmpty()) {
      throw new Exception(BLOODGROUP_NAME_MUST_BE_NOT_NULL);
    }

    BloodGroup entity = BloodGroupEntityMapper.mapDtoToEntity(dto);
    entity = bloodGroupRepository.save(entity);

    return entity.getId();
  }

}
