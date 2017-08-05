package com.teammental.common.bll.service;

import java.util.List;

import com.teammental.common.bll.dto.IdNameDto;
import com.teammental.common.exception.NotFoundException;

public interface CommonService {
  List<IdNameDto> getCities() throws NotFoundException;

  List<IdNameDto> getDistrictsByCityId(Integer cityId) throws NotFoundException;
}
