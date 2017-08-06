package com.teammental.common.bll.service;

import com.teammental.common.bll.dto.IdNameDto;
import com.teammental.common.exception.NotFoundException;

import java.util.List;

public interface CommonService {
  List<IdNameDto> getCities() throws NotFoundException;

  List<IdNameDto> getDistrictsByCityId(Integer cityId) throws NotFoundException;
}
