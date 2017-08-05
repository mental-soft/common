package com.teammental.common.bll.service;

import java.util.List;
import java.util.Optional;

import com.teammental.common.bll.dto.IdNameDto;
import com.teammental.common.dal.entity.City;
import com.teammental.common.dal.entity.District;
import com.teammental.common.dal.repository.CityRepository;
import com.teammental.common.dal.repository.DistrictRepository;
import com.teammental.memapper.MeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

@Service
public class CommonServiceImpl implements CommonService {

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private DistrictRepository districtRepository;

  @Override
  public List<IdNameDto> getCities() {
    List<City> cities = cityRepository.findAll();

    Optional<List<IdNameDto>> dtosOptional = MeMapper.getMapperFromList(cities)
        .mapToList(IdNameDto.class);

    if (dtosOptional.isPresent() && dtosOptional.get().size() > 0) {
      return dtosOptional.get();
    }

    throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
  }

  @Override
  public List<IdNameDto> getDistrictsByCityId(Integer cityId) {
    List<District> districts = districtRepository.findAllByCityId(cityId);

    Optional<List<IdNameDto>> dtosOptional = MeMapper.getMapperFromList(districts)
        .mapToList(IdNameDto.class);

    if (dtosOptional.isPresent() && dtosOptional.get().size() > 0) {
      return dtosOptional.get();
    }

    throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
  }
}
