package service;

import dto.CityDto;
import dto.CountryDto;
import entity.City;
import jpa.CityRepository;
import mapper.CityListDtoMapper;
import mapper.DtoToCityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by okan on 12.02.2017.
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;


    @Override
    public List<CityDto> getAllCity() {
        List<City> list = cityRepository.findAll();
        return CityListDtoMapper.mapEntitiesToDtos(list);
    }

    @Override
    public List<CityDto> getAllCityByCountry(Integer Country_Id) {

        List<City> list = cityRepository.findByCountry_Id(Country_Id);
        return CityListDtoMapper.mapEntitiesToDtos(list);
    }

    @Override
    public CityDto getCity(CityDto citydto) {
        City city = cityRepository.findOne(citydto.getId());
        return CityListDtoMapper.mapEntityToDto(city);
    }

    @Override
    public void insertCity(CityDto citydto) {
        City city = DtoToCityMapper.mapDtoToEntity(citydto);
        cityRepository.save(city);
    }

    @Override
    public void updateCity(CityDto citydto) {
        City city = DtoToCityMapper.mapDtoToEntity(citydto);
        cityRepository.save(city);
    }

    @Override
    public void deleteCity(CityDto citydto) {
        cityRepository.delete(citydto.getId());
    }
}
