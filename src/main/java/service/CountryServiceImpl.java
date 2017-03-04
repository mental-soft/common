package service;

import dto.CountryDto;
import entity.Country;
import jpa.CountryRepository;
import mapper.CountryListDtoMapper;
import mapper.DtoToCountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Coşkun on 4.2.2017.
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;

    @Override
    public long countryActiveCount() {
        return countryRepository.count();
    }

    @Override
    public List<CountryDto> getAllCountry() {
        List<Country> list = countryRepository.findAll();
        return CountryListDtoMapper.mapEntitiesToDtos(list);
    }

    @Override
    public CountryDto getCountry(CountryDto countrydto) {
        Country country = countryRepository.findOne(countrydto.getId());
        return CountryListDtoMapper.mapEntityToDto(country);
    }

    @Override
    public void insertCountry(CountryDto countrydto) {
        Country country = DtoToCountryMapper.mapDtoToEntity(countrydto);
        countryRepository.save(country);
    }

    @Override
    public void updateCountry(CountryDto countrydto) {
        Country country = DtoToCountryMapper.mapDtoToEntity(countrydto);
        countryRepository.save(country);
    }

    @Override
    public void deleteCountry(CountryDto countrydto) {
        countryRepository.delete(countrydto.getId());
    }

}
