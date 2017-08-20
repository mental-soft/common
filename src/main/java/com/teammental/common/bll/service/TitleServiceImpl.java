package com.teammental.common.bll.service;

import com.teammental.common.bll.dto.TitleDto;
import com.teammental.common.dal.entity.Title;
import com.teammental.common.dal.repository.TitleRepository;
import com.teammental.common.exception.NotFoundException;
import com.teammental.memapper.MeMapper;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class TitleServiceImpl implements TitleService {

  @Autowired
  private TitleRepository titleRepository;

  @Autowired
  private MessageSource messageSource;

  @Override
  public List<TitleDto> findAll() throws NotFoundException {

    List<Title> titles = titleRepository.findAll();
    Optional<List<TitleDto>> titleDtosOptional = MeMapper.getMapperFromList(titles)
        .mapToList(TitleDto.class);

    if (titleDtosOptional.isPresent() && titleDtosOptional.get().size() > 0) {
      return titleDtosOptional.get();
    }

    throw new NotFoundException(messageSource.getMessage("exception.notfound.title",
        null, Locale.getDefault()));
  }
}
