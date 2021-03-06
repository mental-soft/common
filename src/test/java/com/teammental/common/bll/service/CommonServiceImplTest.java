package com.teammental.common.bll.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.teammental.common.bll.dto.IdNameDto;
import com.teammental.common.config.CityAndDisctictDataGenerator;
import com.teammental.common.dal.entity.City;
import com.teammental.common.dal.entity.District;
import com.teammental.common.dal.repository.CityRepository;
import com.teammental.common.dal.repository.DistrictRepository;
import com.teammental.meconfig.exception.entity.EntityNotFoundException;
import com.teammental.memapper.MeMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
public class CommonServiceImplTest {

  @MockBean
  private CityRepository cityRepository;

  @MockBean
  private DistrictRepository districtRepository;

  @InjectMocks
  private CommonService commonService = new CommonServiceImpl();

  @MockBean
  private MessageSource messageSource;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }


  @Test
  public void shouldReturnAllCities_whenCitiesFound() throws EntityNotFoundException {
    List<City> expectedCities = CityAndDisctictDataGenerator.prepareRandomListOfCities();

    when(cityRepository.findAll())
        .thenReturn(expectedCities);

    Optional<List<IdNameDto>> expectedDtosOptional = MeMapper.getMapperFromList(expectedCities)
        .mapToList(IdNameDto.class);
    List<IdNameDto> expectedDtos = expectedDtosOptional.get();

    List<IdNameDto> actualDtos = commonService.findAllCities();

    String expectedDtoString = expectedDtos.stream()
        .map(idNameDto -> idNameDto.toString())
        .reduce("", String::concat);

    String actualDtoString = actualDtos.stream()
        .map(idNameDto -> idNameDto.toString())
        .reduce("", String::concat);

    assertEquals(expectedDtoString, actualDtoString);

    verify(cityRepository, times(1)).findAll();
  }

  @Test(expected = EntityNotFoundException.class)
  public void shouldThrowNotFoundException_whenNoCityFound() throws EntityNotFoundException {
    List<City> exceptedCities = new ArrayList<>();
    when(cityRepository.findAll())
        .thenReturn(exceptedCities);
    commonService.findAllCities();
    verify(cityRepository, times(1)).findAll();
  }

  @Test
  public void shouldReturnDistrictsByCityId_whenFoundAny() throws EntityNotFoundException {
    City expectedCity = CityAndDisctictDataGenerator.prepareRandomCity();
    List<District> expectedDistricts = CityAndDisctictDataGenerator
        .prepareRandomListOfDistrict(expectedCity, 10);

    when(districtRepository.findAllByCityId(anyInt()))
        .thenReturn(expectedDistricts);

    Optional<List<IdNameDto>> expectedDtosOptional = MeMapper.getMapperFromList(expectedDistricts)
        .mapToList(IdNameDto.class);
    List<IdNameDto> expectedDtos = expectedDtosOptional.get();

    List<IdNameDto> actualDtos = commonService.findDistrictsByCityId(expectedCity.getId());

    String exptectedDtoString = expectedDtos.stream()
        .map(idNameDto -> idNameDto.toString())
        .reduce("", String::concat);

    String actualDtoString = actualDtos.stream()
        .map(idNameDto -> idNameDto.toString())
        .reduce("", String::concat);

    assertEquals(exptectedDtoString, actualDtoString);

    verify(districtRepository, times(1)).findAllByCityId(anyInt());
  }

  @Test(expected = EntityNotFoundException.class)
  public void shouldThrowNotFoundException_whenNoDistrictFound() throws EntityNotFoundException {
    List<District> expectedDistricts = new ArrayList<>();
    when(districtRepository.findAllByCityId(anyInt()))
        .thenReturn(expectedDistricts);

    commonService.findDistrictsByCityId(1);
    verify(districtRepository, times(1)).findAllByCityId(anyInt());
  }

}