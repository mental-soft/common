package com.teammental.common.bll.service;

import com.teammental.common.bll.dto.IdNameDto;
import com.teammental.meconfig.exception.entity.EntityNotFoundException;

import java.util.List;

public interface CommonService {

  List<IdNameDto> findAllCities() throws EntityNotFoundException;

  List<IdNameDto> findDistrictsByCityId(Integer cityId) throws EntityNotFoundException;
}
