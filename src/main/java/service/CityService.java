package service;

import dto.CityDto;
import dto.CountryDto;
import java.util.List;
/**
 * Created by okan on 12.02.2017.
 */
public interface CityService {

    List<CityDto> getAllCity();

    List<CityDto> getAllCityByCountry(Integer Country_Id) ;

    CityDto getCity(CityDto citydto);

    void insertCity(CityDto citydto);

    void updateCity(CityDto citydto);

    void deleteCity(CityDto citydto);

}
