package com.teammental.common.bll.service;

import java.util.List;

import com.teammental.common.bll.dto.IdNameDto;

public interface CommonService {
  List<IdNameDto> getCities();

  List<IdNameDto> getDistrictsByCityId(Integer cityId);
}
