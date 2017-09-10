package com.teammental.common.web.controller;

import java.util.List;

import com.teammental.common.bll.dto.TitleDto;
import com.teammental.common.bll.service.TitleService;
import com.teammental.meconfig.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TitleController {
  @Autowired
  private TitleService titleService;

  @GetMapping("/titles")
  public List<TitleDto> getTitles() throws NotFoundException {
    return titleService.findAll();
  }
}
