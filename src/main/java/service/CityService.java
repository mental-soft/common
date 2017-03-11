package service;

import dto.CityDto;
import dto.CountryDto;
import java.util.List;
/**
 * Created by okan on 12.02.2017.
 */
public interface CityService {

    List<CityDto> getAllCityByCountry(Integer countryID) ;

    Boolean existCityByCountry(Integer countryID);

    List<CityDto> getAll();

    CityDto getByID(Integer cityID) throws Exception;

    void deleteByID(Integer cityID) throws Exception;

    int saveOrUpdate(CityDto dto) throws Exception;



}
