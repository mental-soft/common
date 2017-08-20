package com.teammental.common.bll.service;

import com.teammental.common.bll.dto.Dto;
import com.teammental.common.exception.NotFoundException;

import java.util.List;

public interface BaseService<T extends Dto> {
  List<T> findAll() throws NotFoundException;
}
