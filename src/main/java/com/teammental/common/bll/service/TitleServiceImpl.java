package com.teammental.common.bll.service;

import com.teammental.common.bll.dto.TitleDto;
import com.teammental.common.dal.repository.TitleRepository;
import com.teammental.meconfig.bll.service.BaseCrudServiceImpl;
import com.teammental.meconfig.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TitleServiceImpl extends BaseCrudServiceImpl<TitleDto, Integer>
    implements TitleService {

  @Autowired
  private TitleRepository titleRepository;

  @Override
  protected JpaRepository getRepository() {
    return titleRepository;
  }

  @Override
  protected Class<?> getDtoClass() {
    return TitleDto.class;
  }


  @Override
  public TitleDto findById(Integer id) throws NotFoundException {
    return null;
  }

  @Override
  public Integer insertOrUpdate(TitleDto dto) {
    return null;
  }
}
