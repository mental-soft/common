package com.teammental.common.bll.service;

import com.teammental.common.bll.dto.IdNameDto;
import com.teammental.common.exception.NotFoundException;

import java.util.List;

public interface CommonService extends BaseService<IdNameDto> {

  List<IdNameDto> findDistrictsByCityId(Integer cityId) throws NotFoundException;
}
