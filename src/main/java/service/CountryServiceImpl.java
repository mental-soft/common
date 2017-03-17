package service;

import dto.CountryDto;
import entity.Country;
import jpa.CountryRepository;
import mapper.dto.CountryListDtoMapper;
import mapper.entity.CountryEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Coşkun on 4.2.2017.
 */
@Service
public class CountryServiceImpl implements CountryService {

  //region Messages
  public static final String NOT_FOUND_MESSAGE = "Ülke kaydı bulunamadı.";
  public static final String PARAMETERS_MUST_BE_NOT_NULL = "Parametre girilmesi gerekmektedir.";
  public static final String COUNTRY_NAME_MUST_BE_NOT_NULL = "Ülke Adı girilmesi gerekmektedir.";
  public static final String COUNTRY_SHOULD_NOT_HAVE_CITY = "Ülkeye ait şehirler bulunmaktadır.";
  //endregion

  @Autowired
  CountryRepository countryRepository;

  @Autowired
  CityService cityservice;


  @Override
  public List<CountryDto> getAll() {
    List<Country> list = countryRepository.findAll();
    return CountryListDtoMapper.mapEntitiesToDtos(list);
  }

  @Override
  public CountryDto getByID(int countryID) throws Exception {
    Country entity = countryRepository.findOne(countryID);

    if (entity == null) {
      throw new Exception(NOT_FOUND_MESSAGE);
    }

    return CountryListDtoMapper.mapEntityToDto(entity);
  }

  @Override
  public void deleteByID(int countryID) throws Exception {
    //city varsa kontrol yapılacak.
    if (cityservice.existCityByCountry(countryID)) {
      throw new Exception(COUNTRY_SHOULD_NOT_HAVE_CITY);
    }
    countryRepository.delete(countryID);
  }

  @Override
  public int saveOrUpdate(CountryDto dto) throws Exception {
    if (dto == null) {
      throw new Exception(PARAMETERS_MUST_BE_NOT_NULL);
    }

    if (dto.getName() == null || dto.getName().isEmpty()) {
      throw new Exception(COUNTRY_NAME_MUST_BE_NOT_NULL);
    }

    Country entity = CountryEntityMapper.mapDtoToEntity(dto);
    entity = countryRepository.save(entity);

    return entity.getId();
  }
}
