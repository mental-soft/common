package com.teammental.common.bll.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.teammental.common.bll.dto.TitleDto;
import com.teammental.common.config.TitleDataGenerator;
import com.teammental.common.dal.entity.Title;
import com.teammental.common.dal.repository.TitleRepository;
import com.teammental.meconfig.exception.entity.EntityNotFoundException;
import com.teammental.memapper.MeMapper;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SuppressWarnings({"PMD.TooManyStaticImports", "PMD.UnusedPrivateField"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TitleServiceImplTest {


  @MockBean
  private TitleRepository titleRepository;

  @InjectMocks
  private TitleService titleService = new TitleServiceImpl();

  @MockBean
  private MessageSource messageSource;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldReturnAllList_whenFoundAny() throws EntityNotFoundException {
    final int expectedSize = 10;
    final List<Title> expectedTitles = TitleDataGenerator.prepateRandomListOfTitle(expectedSize);
    when(titleRepository.findAll())
        .thenReturn(expectedTitles);
    final List<TitleDto> expectedDtos = (List<TitleDto>) MeMapper.getMapperFromList(expectedTitles)
        .mapToList(TitleDto.class).get();
    final String expectedDtoString = expectedDtos.stream()
        .map(titleDto -> titleDto.toString())
        .reduce("", String::concat);


    List<TitleDto> actualDtos = titleService.findAll();
    String actualDtoString = actualDtos.stream()
        .map(titleDto -> titleDto.toString())
        .reduce("", String::concat);

    assertEquals(expectedDtoString, actualDtoString);

    verify(titleRepository, times(1)).findAll();
  }


  @Test(expected = EntityNotFoundException.class)
  public void shouldThrowNotFoundException_whenNotFoundAny() throws EntityNotFoundException {
    when(titleRepository.findAll())
        .thenReturn(null);

    titleService.findAll();

    verify(titleRepository, times(1)).findAll();
  }
}