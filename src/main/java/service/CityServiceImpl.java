package service;

import dto.CityDto;
import entity.City;
import jpa.CityRepository;
import mapper.dto.CityListDtoMapper;
import mapper.entity.CityEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by okan on 12.02.2017.
 */
@Service
public class CityServiceImpl implements CityService {

    //region Messages
    public static final String NOT_FOUND_MESSAGE = "Ülke kaydı bulunamadı.";
    public static final String PARAMETERS_MUST_BE_NOT_NULL = "Parametre girilmesi gerekmektedir.";
    public static final String CITY_NAME_MUST_BE_NOT_NULL = "İl Adı girilmesi gerekmektedir.";
    public static final String CITY_SHOULD_NOT_HAVE_DISTRICT = "İlin ilçeleri bulunmaktadır.";
    //endregion

    @Autowired
    CityRepository cityRepository;

    @Autowired
    DistrictService districtService;


    @Override
    public List<CityDto> getAllCityByCountry(Integer countryID) {
        List<City> list = cityRepository.findByCountry_Id(countryID);
        return CityListDtoMapper.mapEntitiesToDtos(list);
    }

    @Override
    public Boolean existCityByCountry(Integer countryID) {
          return  cityRepository.countByCountry_Id(countryID)>0 ? true:false;
    }

    @Override
    public List<CityDto> getAll() {
        List<City> list = cityRepository.findAll();
        return CityListDtoMapper.mapEntitiesToDtos(list);
    }

    @Override
    public CityDto getByID(Integer cityID) throws Exception {
        City entity = cityRepository.findOne(cityID);

        if(entity == null) {
            throw new Exception(NOT_FOUND_MESSAGE);
        }

        return CityListDtoMapper.mapEntityToDto(entity);
    }

    @Override
    public void deleteByID(Integer cityID) throws Exception {
        //city varsa kontrol yapılacak.
        if(districtService.existDistrictByCity(cityID)){
            throw new Exception(CITY_SHOULD_NOT_HAVE_DISTRICT);
        }
        cityRepository.delete(cityID);
    }

    @Override
    public int saveOrUpdate(CityDto dto) throws Exception {
        if(dto == null) {
            throw new Exception(PARAMETERS_MUST_BE_NOT_NULL);
        }

        if(dto.getName() == null || dto.getName().isEmpty()) {
            throw new Exception(CITY_NAME_MUST_BE_NOT_NULL);
        }

        City entity = CityEntityMapper.mapDtoToEntity(dto);
        entity = cityRepository.save(entity);

        return entity.getId();
    }
}
