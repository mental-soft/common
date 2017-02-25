package service;

import dto.CountryListDto;
import entity.Country;
import jpa.CountryRepository;
import mapper.dto.CountryListDtoMapper;
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
    public List<CountryListDto> getAllCountry() {
        List<Country> list = countryRepository.findAll();
        return CountryListDtoMapper.mapEntitiesToDtos(list);
    }

}
