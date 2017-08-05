package com.teammental.common.web.controller;

import java.util.List;

import com.teammental.common.bll.dto.IdNameDto;
import com.teammental.common.bll.service.CommonService;
import com.teammental.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

  @Autowired
  private CommonService commonService;

  @GetMapping("/cities")
  public List<IdNameDto> getCities() throws NotFoundException {
    return commonService.getCities();
  }
}
