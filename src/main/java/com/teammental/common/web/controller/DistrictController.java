package com.teammental.common.web.controller;

import com.teammental.common.bll.dto.IdNameDto;
import com.teammental.common.bll.service.CommonService;
import com.teammental.common.config.UrlConfig;
import com.teammental.common.exception.NotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistrictController {

  @Autowired
  private CommonService commonService;

  @GetMapping(UrlConfig.DistrictControllerConfig.URL_GET_DISTRICTS_BY_CITY_ID)
  public List<IdNameDto> getDistrictsByCityId(@RequestParam("cityId") int cityId)
      throws NotFoundException {

    return commonService.findDistrictsByCityId(cityId);
  }
}
