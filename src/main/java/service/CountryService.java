package service;

import dto.CountryDto;

import java.util.List;

/**
 * Created by Coşkun on 4.2.2017.
 */
public interface CountryService {

  List<CountryDto> getAll();

  CountryDto getByID(int countryID) throws Exception;

  void deleteByID(int countryID) throws Exception;

  int saveOrUpdate(CountryDto dto) throws Exception;

}
