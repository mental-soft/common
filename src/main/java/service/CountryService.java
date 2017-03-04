package service;

import dto.CountryDto;

import java.util.List;

/**
 * Created by Coşkun on 4.2.2017.
 */
public interface CountryService {

    long countryActiveCount();

    List<CountryDto> getAllCountry();

    CountryDto getCountry(CountryDto countrydto);

    void insertCountry(CountryDto countrydto);

    void updateCountry(CountryDto countrydto);

    void deleteCountry(CountryDto countrydto);

}
