package service;

import dto.DistrictDto;

import java.util.List;

/**
 * Created by okan on 12.02.2017.
 */
public interface DistrictService {

  List<DistrictDto> getAllDistrictByCity(Integer cityId);

  List<DistrictDto> getAll();

  Boolean existDistrictByCity(Integer cityId);

  DistrictDto getById(int districtId) throws Exception;

  void deleteById(int districtId) throws Exception;

  int saveOrUpdate(DistrictDto dto) throws Exception;


}
