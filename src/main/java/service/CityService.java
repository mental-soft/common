package service;

import dto.CityDto;
import dto.CountryDto;

import java.util.List;

/**
 * Created by okan on 12.02.2017.
 */
public interface CityService {

  List<CityDto> getAllCityByCountry(Integer countryId);

  Boolean existCityByCountry(Integer countryId);

  List<CityDto> getAll();

  CityDto getById(Integer cityId) throws Exception;

  void deleteById(Integer cityId) throws Exception;

  int saveOrUpdate(CityDto dto) throws Exception;


}
