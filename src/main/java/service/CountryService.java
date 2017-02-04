package service;

import dto.CountryListDto;

import java.util.List;

/**
 * Created by Coşkun on 4.2.2017.
 */
public interface CountryService {

    long countryActiveCount();

    List<CountryListDto> getAllCountry();

}
