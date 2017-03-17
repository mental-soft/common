package service;

import dto.DistrictDto;
import entity.District;
import jpa.DistrictRepository;
import mapper.dto.DistrictListDtoMapper;
import mapper.entity.DistrictEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by okan on 15.02.2017.
 */
@Service
public class DistrictServiceImpl implements DistrictService {

  //region Messages
  public static final String NOT_FOUND_MESSAGE = "Ülke kaydı bulunamadı.";
  public static final String PARAMETERS_MUST_BE_NOT_NULL = "Parametre girilmesi gerekmektedir.";
  public static final String DISTRICT_NAME_MUST_BE_NOT_NULL = "Ülke Adı girilmesi gerekmektedir.";
  //endregion

  @Autowired
  DistrictRepository districtRepository;


  @Override
  public List<DistrictDto> getAllDistrictByCity(Integer cityId) {
    List<District> list = districtRepository.findByCity_Id(cityId);
    return DistrictListDtoMapper.mapEntitiesToDtos(list);
  }

  @Override
  public List<DistrictDto> getAll() {
    List<District> list = districtRepository.findAll();
    return DistrictListDtoMapper.mapEntitiesToDtos(list);
  }

  @Override
  public Boolean existDistrictByCity(Integer cityId) {
    return districtRepository.countByCity_Id(cityId) > 0 ? true : false;
  }

  @Override
  public DistrictDto getById(int districtId) throws Exception {
    District entity = districtRepository.getOne(districtId);

    if (entity == null) {
      throw new Exception(NOT_FOUND_MESSAGE);
    }

    return DistrictListDtoMapper.mapEntityToDto(entity);
  }

  @Override
  public void deleteById(int districtId) {
    districtRepository.delete(districtId);
  }

  @Override
  public int saveOrUpdate(DistrictDto dto) throws Exception {
    if (dto == null) {
      throw new Exception(PARAMETERS_MUST_BE_NOT_NULL);
    }

    if (dto.getName() == null || dto.getName().isEmpty()) {
      throw new Exception(DISTRICT_NAME_MUST_BE_NOT_NULL);
    }

    District entity = DistrictEntityMapper.mapDtoToEntity(dto);
    entity = districtRepository.save(entity);

    return entity.getId();
  }
}
