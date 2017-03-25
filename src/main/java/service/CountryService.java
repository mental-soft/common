package service;

import dto.CountryDto;

import java.util.List;

/**
 * Created by Coşkun on 4.2.2017.
 */
public interface CountryService {

  List<CountryDto> getAll();

  CountryDto getById(int countryId) throws Exception;

  void deleteById(int countryId) throws Exception;

  int saveOrUpdate(CountryDto dto) throws Exception;

}
