package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;

import config.DistrictServiceTestConfig;
import dto.CityDto;
import dto.CountryDto;
import dto.DistrictDto;
import entity.City;
import entity.Country;
import entity.District;

import java.util.ArrayList;
import java.util.List;

import jpa.DistrictRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by demirtasokan on 07.03.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DistrictServiceTestConfig.class})
public class DistrictServiceTest {

  //TODO Tecnical Depth1: Tekrar eden kodlar düzenlenecek.

  @Autowired
  DistrictRepository repository;

  @Autowired
  DistrictService service;

  @Before
  public void before() {
    reset(repository);
  }

  @Test
  public void service_IsNotNull() {
    assertNotNull(service);
  }

  //region getAll()
  @Test
  public void getAll_WhenEmpty_ShouldReturnSize() {
    given(repository.findAll()).willReturn(new ArrayList<>());
    //when(repository.findAll()).thenReturn(new ArrayList<>());

    List<DistrictDto> list = service.getAll();

    assertEquals(0, list.size());
  }

  @Test
  public void getAll_WhenFull_ShouldReturnSize() {
    Country country = Country.getBuilder()
        .id(1)
        .name("Türkiye")
        .active(true)
        .build();

    City city = City.getBuilder()
        .id(1)
        .name("Ankara")
        .active(true)
        .country(country)
        .build();

    List<District> entityList = new ArrayList<>();
    entityList.add(District.getBuilder()
        .id(1)
        .name("China")
        .city(city)
        .active(true)
        .build());

    entityList.add(District.getBuilder()
        .id(2)
        .name("Korea")
        .city(city)
        .active(true)
        .build());

    entityList.add(District.getBuilder()
        .id(3)
        .name("Australia")
        .city(city)
        .active(true)
        .build());

    given(repository.findAll()).willReturn(entityList);

    List<DistrictDto> list = service.getAll();

    assertEquals(3, list.size());
  }

  @Test
  public void getAll_WhenFull_ShouldReturnInfo() {

    Country country = Country.getBuilder()
        .id(1)
        .name("Türkiye")
        .active(true)
        .build();

    City city = City.getBuilder()
        .id(1)
        .name("Ankara")
        .active(true)
        .country(country)
        .build();

    List<District> entityList = new ArrayList<>();

    entityList.add(District.getBuilder()
        .id(1)
        .name("A")
        .city(city)
        .active(true)
        .build());

    entityList.add(District.getBuilder()
        .id(2)
        .name("B")
        .active(true)
        .city(city)
        .build());

    entityList.add(District.getBuilder()
        .id(3)
        .name("C")
        .active(true)
        .city(city)
        .build());

    given(repository.findAll()).willReturn(entityList);

    DistrictDto dto = service.getAll().get(0);
    int id = dto.getId();
    assertEquals(1, id);
    assertEquals("A", dto.getName());
    assertEquals(true, dto.getActive());
  }
  //endregion()

  //region getById()

  //endregion

  //region deleteById()


  //endregion()

  //region getById()
  @Test
  public void getById_WhenEmpty_ShouldReturnException() {
    given(repository.findOne(anyInt())).willReturn(null);

    try {
      service.getById(anyInt());
      Assert.fail();
    } catch (Exception e) {
      assertEquals(DistrictServiceImpl.NOT_FOUND_MESSAGE, e.getMessage());
    }
  }

  @Test
  public void getById_WhenFull_ShouldReturnInfo() {
    District entity;

    Country country = Country.getBuilder()
        .id(1)
        .name("Türkiye")
        .active(true)
        .build();

    City city = City.getBuilder()
        .id(1)
        .name("Ankara")
        .active(true)
        .country(country)
        .build();

    entity = District.getBuilder()
        .id(1)
        .name("A")
        .active(true)
        .city(city)
        .build();

    given(repository.findOne(anyInt())).willReturn(entity);

    try {
      DistrictDto dto = service.getById(anyInt());

      int id = dto.getId();
      assertEquals(1, id);
      assertEquals("A", dto.getName());
      assertEquals(true, dto.getActive());
    } catch (Exception e) {
      Assert.fail();
    }
  }
  //endregion

  //region deleteById()
  @Test
  public void deleteById_WhenEmpty_ShouldReturnException() {
    doThrow(EmptyResultDataAccessException.class).when(repository).delete(anyInt());

    try {
      service.deleteById(anyInt());
      Assert.fail();
    } catch (Exception e) {
      assertEquals(EmptyResultDataAccessException.class, e.getClass());
    }
  }

  @Test
  public void deleteById_WhenFull_ShouldDeleteSuccess() {
    try {
      service.deleteById(anyInt());
    } catch (Exception e) {
      Assert.fail();
    }
  }
  //endregion

  //region saveOrUpdate()
  @Test
  public void saveOrUpdate_WhenDtoEmpty_ShouldReturnException() {
    try {
      service.saveOrUpdate(null);
      Assert.fail();
    } catch (Exception e) {
      assertEquals(DistrictServiceImpl.PARAMETERS_MUST_BE_NOT_NULL, e.getMessage());
    }
  }

  @Test
  public void saveOrUpdate_WhenDtoNameEmpty_ShouldReturnException() {
    try {
      service.saveOrUpdate(DistrictDto.getBuilder().build());
      Assert.fail();
    } catch (Exception e) {
      assertEquals(DistrictServiceImpl.DISTRICT_NAME_MUST_BE_NOT_NULL, e.getMessage());
    }
  }

  @Test
  public void saveOrUpdate_WhenDtoFull_ShouldReturnEntityId() {
    District entity;

    Country country = Country.getBuilder()
        .id(1)
        .name("Türkiye")
        .active(true)
        .build();

    City city = City.getBuilder()
        .id(1)
        .name("Ankara")
        .active(true)
        .country(country)
        .build();

    entity = District.getBuilder()
        .id(1)
        .name("A")
        .active(true)
        .city(city)
        .build();

    given(repository.save(any(District.class))).willReturn(entity);

    try {

      CountryDto countryDto = CountryDto.getBuilder()
          .id(1)
          .name("Türkiye")
          .active(true)
          .build();

      CityDto cityDto = CityDto.getBuilder()
          .id(1)
          .name("Ankara")
          .active(true)
          .countryDto(countryDto)
          .build();

      DistrictDto dto = DistrictDto.getBuilder()
          .id(1)
          .name("A")
          .active(true)
          .cityDto(cityDto)
          .build();

      int entityId = service.saveOrUpdate(dto);
      assertEquals(1, entityId);
    } catch (Exception e) {
      Assert.fail();
    }
  }
  //endregion
}
