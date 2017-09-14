package com.teammental.common.web.controller;

import com.teammental.common.bll.dto.TitleDto;
import com.teammental.common.bll.service.TitleService;
import com.teammental.meconfig.web.controller.BaseCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/titles")
public class TitleController extends BaseCrudController<TitleService, TitleDto, Integer> {
  @Autowired
  private TitleService titleService;

  @Override
  protected TitleService getBaseCrudService() {
    return titleService;
  }

  @Override
  protected String getMappingUrlOfController() {
    return "/titles";
  }

}
