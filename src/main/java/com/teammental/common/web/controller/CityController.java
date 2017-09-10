package com.teammental.common.web.controller;

import com.teammental.common.bll.dto.IdNameDto;
import com.teammental.common.bll.service.CommonService;
import com.teammental.common.config.UrlConfig;
import com.teammental.meconfig.exception.NotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

  @Autowired
  private CommonService commonService;

  @GetMapping(UrlConfig.CityControllerConfig.URL_GET_CITIES)
  public List<IdNameDto> getCities() throws NotFoundException {
    return commonService.findAllCities();
  }
}
