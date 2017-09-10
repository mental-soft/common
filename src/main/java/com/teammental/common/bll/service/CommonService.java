package com.teammental.common.bll.service;

import com.teammental.common.bll.dto.IdNameDto;
import com.teammental.meconfig.exception.NotFoundException;

import java.util.List;

public interface CommonService {

  List<IdNameDto> findAllCities() throws NotFoundException;

  List<IdNameDto> findDistrictsByCityId(Integer cityId) throws NotFoundException;
}
