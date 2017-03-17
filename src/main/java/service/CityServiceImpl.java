package service;

import dto.CityDto;
import entity.City;
import jpa.CityRepository;
import mapper.dto.CityListDtoMapper;
import mapper.entity.CityEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by okan on 12.02.2017.
 */
@Service
public class CityServiceImpl implements CityService {

  //region Messages
  public static final String NOT_FOUND_MESSAGE = "Ülke kaydı bulunamadı.";
  public static final String PARAMETERS_MUST_BE_NOT_NULL = "Parametre girilmesi gerekmektedir.";
  public static final String CITY_NAME_MUST_BE_NOT_NULL = "Şehir Adı girilmesi gerekmektedir.";
  public static final String CITY_SHOULD_NOT_HAVE_DISTRICT = "Şehire ait şehirler bulunmaktadır.";
  //endregion

  @Autowired
  CityRepository cityRepository;

  @Autowired
  DistrictService districtService;


  @Override
  public List<CityDto> getAllCityByCountry(Integer countryId) {
    List<City> list = cityRepository.findByCountry_Id(countryId);
    return CityListDtoMapper.mapEntitiesToDtos(list);
  }

  @Override
  public Boolean existCityByCountry(Integer countryId) {
    return cityRepository.countByCountry_Id(countryId) > 0 ? true : false;
  }

  @Override
  public List<CityDto> getAll() {
    List<City> list = cityRepository.findAll();
    return CityListDtoMapper.mapEntitiesToDtos(list);
  }

  @Override
  public CityDto getById(Integer cityId) throws Exception {
    City entity = cityRepository.getOne(cityId);

    if (entity == null) {
      throw new Exception(NOT_FOUND_MESSAGE);
    }

    return CityListDtoMapper.mapEntityToDto(entity);
  }

  @Override
  public void deleteById(Integer cityId) throws Exception {
    //city varsa kontrol yapılacak.
    if (districtService.existDistrictByCity(cityId)) {
      throw new Exception(CITY_SHOULD_NOT_HAVE_DISTRICT);
    }
    cityRepository.delete(cityId);
  }

  @Override
  public int saveOrUpdate(CityDto dto) throws Exception {
    if (dto == null) {
      throw new Exception(PARAMETERS_MUST_BE_NOT_NULL);
    }

    if (dto.getName() == null || dto.getName().isEmpty()) {
      throw new Exception(CITY_NAME_MUST_BE_NOT_NULL);
    }

    City entity = CityEntityMapper.mapDtoToEntity(dto);
    entity = cityRepository.save(entity);

    return entity.getId();
  }
}
