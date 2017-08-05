package com.teammental.common.bll.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.teammental.common.bll.dto.IdNameDto;
import com.teammental.common.dal.entity.City;
import com.teammental.common.dal.entity.District;
import com.teammental.common.dal.repository.CityRepository;
import com.teammental.common.dal.repository.DistrictRepository;
import com.teammental.mebuilder.GenericBuilder;
import com.teammental.memapper.MeMapper;
import com.teammental.memapper.util.StringUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringJUnit4ClassRunner.class)
public class CommonServiceImplTest {

  @MockBean
  private CityRepository cityRepository;

  @MockBean
  private DistrictRepository districtRepository;

  @InjectMocks
  private CommonService commonService = new CommonServiceImpl();

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }


  @Test
  public void shouldReturnAllCities_whenCitiesFound() {
    List<City> expectedCities = prepareRandomListOfCities();

    when(cityRepository.findAll())
        .thenReturn(expectedCities);

    Optional<List<IdNameDto>> expectedDtosOptional = MeMapper.getMapperFromList(expectedCities)
        .mapToList(IdNameDto.class);
    List<IdNameDto> expectedDtos = expectedDtosOptional.get();

    List<IdNameDto> actualDtos = commonService.getCities();

    String expectedDtoString = expectedDtos.stream()
        .map(idNameDto -> idNameDto.toString())
        .reduce("", String::concat);

    String actualDtoString = actualDtos.stream()
        .map(idNameDto -> idNameDto.toString())
        .reduce("", String::concat);

    assertEquals(expectedDtoString, actualDtoString);

    verify(cityRepository, times(1)).findAll();
  }

  @Test
  public void shouldThrowNotFoundException_whenNoCityFound() {
    List<City> exceptedCities = new ArrayList<>();
    when(cityRepository.findAll())
        .thenReturn(exceptedCities);
    try {
      commonService.getCities();
      fail();
    } catch (HttpClientErrorException ex) {
      assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }
    verify(cityRepository, times(1)).findAll();
  }

  @Test
  public void shouldReturnDistrictsByCityId_whenFoundAny() {
    City expectedCity = prepareRandomCity();
    List<District> expectedDistricts = prepateRandomListOfDistrict(expectedCity);

    when(districtRepository.findAllByCityId(anyInt()))
        .thenReturn(expectedDistricts);

    Optional<List<IdNameDto>> expectedDtosOptional = MeMapper.getMapperFromList(expectedDistricts)
        .mapToList(IdNameDto.class);
    List<IdNameDto> expectedDtos = expectedDtosOptional.get();

    List<IdNameDto> actualDtos = commonService.getDistrictsByCityId(expectedCity.getId());

    String exptectedDtoString = expectedDtos.stream()
        .map(idNameDto -> idNameDto.toString())
        .reduce("", String::concat);

    String actualDtoString = actualDtos.stream()
        .map(idNameDto -> idNameDto.toString())
        .reduce("", String::concat);

    assertEquals(exptectedDtoString, actualDtoString);

    verify(districtRepository, times(1)).findAllByCityId(anyInt());
  }

  @Test
  public void shouldThrowNotFoundException_whenNoDistrictFound() {
    List<District> expectedDistricts = new ArrayList<>();
    when(districtRepository.findAllByCityId(anyInt()))
        .thenReturn(expectedDistricts);

    try {
      commonService.getDistrictsByCityId(1);
      fail();
    } catch (HttpClientErrorException ex) {
      assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    verify(districtRepository, times(1)).findAllByCityId(anyInt());
  }

  private List<City> prepareRandomListOfCities() {
    List<City> cities = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      City city = prepareRandomCity();
      cities.add(city);
    }

    return cities;
  }

  private List<District> prepateRandomListOfDistrict(City city) {
    Random random = new Random();
    List<District> districts = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Integer id = random.nextInt();
      String name = StringUtil.generateRandomString(5);
      District district = GenericBuilder.of(District::new)
          .with(District::setId, id)
          .with(District::setName, name)
          .with(District::setCity, city)
          .build();
      districts.add(district);
    }
    return districts;
  }

  private City prepareRandomCity() {
    Random random = new Random();
    Integer id = random.nextInt();
    String name = StringUtil.generateRandomString(5);

    City city = GenericBuilder.of(City::new)
        .with(City::setId, id)
        .with(City::setName, name)
        .build();

    List<District> districts = prepateRandomListOfDistrict(city);
    city.setDistricts(districts);

    return city;
  }
}