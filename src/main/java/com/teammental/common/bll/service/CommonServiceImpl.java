package com.teammental.common.bll.service;

import com.teammental.common.bll.dto.IdNameDto;
import com.teammental.common.dal.entity.City;
import com.teammental.common.dal.entity.District;
import com.teammental.common.dal.repository.CityRepository;
import com.teammental.common.dal.repository.DistrictRepository;
import com.teammental.common.exception.NotFoundException;
import com.teammental.memapper.MeMapper;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private DistrictRepository districtRepository;

  @Autowired
  private MessageSource messageSource;

  @Override
  public List<IdNameDto> getCities() throws NotFoundException {
    List<City> cities = cityRepository.findAll();

    Optional<List<IdNameDto>> dtosOptional = MeMapper.getMapperFromList(cities)
        .mapToList(IdNameDto.class);

    if (dtosOptional.isPresent() && dtosOptional.get().size() > 0) {
      return dtosOptional.get();
    }

    throw new NotFoundException(messageSource.getMessage("exception.notfound.city",
        null, Locale.getDefault()));
  }

  @Override
  public List<IdNameDto> getDistrictsByCityId(Integer cityId) throws NotFoundException {
    List<District> districts = districtRepository.findAllByCityId(cityId);

    Optional<List<IdNameDto>> dtosOptional = MeMapper.getMapperFromList(districts)
        .mapToList(IdNameDto.class);

    if (dtosOptional.isPresent() && dtosOptional.get().size() > 0) {
      return dtosOptional.get();
    }

    throw new NotFoundException(messageSource.getMessage("exception.notfound.district",
        null, Locale.getDefault()));
  }
}
