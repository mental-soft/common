package service;

import config.CityServiceTestConfig;
import dto.CityDto;
import dto.CountryDto;
import entity.City;
import entity.Country;
import jpa.CityRepository;
import jpa.DistrictRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;

/**
 * Created by demirtasokan on 07.03.2017.
 */

//Gerçek test
//@TestPropertySource({"classpath:application.properties",
//        "classpath:environment/developer.properties"})
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {config.PersistenceContext.class, config.ServiceContext.class})

//Mock test
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CityServiceTestConfig.class})


public class CityServiceTest {

    //TODO Tecnical Depth1: Tekrar eden kodlar düzenlenecek.

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CityService cityService;

    @Autowired
    DistrictRepository districtRepository;


    //      @Test
//    public void GercekRepositoryveService()
//    {
//
//          Boolean varmi = service.existCityByCountry(1);
//        Boolean varmi2 = service.existCityByCountry(2);
//
////        List<City> hepsi = repository.findAll();
////
////        List<CityDto> hep = service.getAll();
////
////     List<CityDto> sehirler =   service.getAllCityByCountry(2);
//    }
    @Before
    public  void before() {
        reset(cityRepository);
        reset(districtRepository);
    }

    @Test
    public void service_IsNotNull() {
        assertNotNull(cityService);
    }

    //region getAll()
    @Test
    public void getAll_WhenEmpty_ShouldReturnSize() {
        given(cityRepository.findAll()).willReturn(new ArrayList<>());
        //when(repository.findAll()).thenReturn(new ArrayList<>());

        List<CityDto> list = cityService.getAll();

        assertEquals(0, list.size());
    }

    @Test
    public void getAll_WhenFull_ShouldReturnSize() {
        List<City> entityList = new ArrayList<>();

        Country country = Country.getBuilder()
                .id(1)
                .name("China")
                .active(true)
                .build();

        entityList.add(City.getBuilder()
                .id(1)
                .name("Adana")
                .active(true)
                .country(country)
                .build());

        entityList.add(City.getBuilder()
                .id(2)
                .name("Mersin")
                .active(true)
                .country(country)
                .build());

        entityList.add(City.getBuilder()
                .id(3)
                .name("Antalya")
                .active(true)
                .country(country)
                .build());

        given(cityRepository.findAll()).willReturn(entityList);

        List<CityDto> list = cityService.getAll();

        assertEquals(3, list.size());
    }

    @Test
    public void getAll_WhenFull_ShouldReturnInfo() {
        List<City> entityList = new ArrayList<>();

        Country country = Country.getBuilder()
                .id(1)
                .name("China")
                .active(true)
                .build();

        entityList.add(City.getBuilder()
                .id(1)
                .name("A")
                .active(true)
                .country(country)
                .build());

        entityList.add(City.getBuilder()
                .id(2)
                .name("B")
                .active(true)
                .country(country)
                .build());

        entityList.add(City.getBuilder()
                .id(3)
                .name("C")
                .active(true)
                .country(country)
                .build());

        given(cityRepository.findAll()).willReturn(entityList);

        CityDto dto = cityService.getAll().get(0);
        int id = dto.getId();
        assertEquals(1, id);
        assertEquals("A", dto.getName());
        assertEquals(true, dto.getActive());
    }
    //endregion()

    //region getByID()
    @Test
    public void getByID_WhenEmpty_ShouldReturnException() {
        given(cityRepository.getOne(anyInt())).willReturn(null);

        try {
            cityService.getByID(anyInt());
            Assert.fail();
        } catch (Exception e) {
            assertEquals(CityServiceImpl.NOT_FOUND_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void getByID_WhenFull_ShouldReturnInfo() {
        City entity = new City();
        Country country = Country.getBuilder()
                .id(1)
                .name("China")
                .active(true)
                .build();

        entity = City.getBuilder()
                .id(1)
                .name("A")
                .active(true)
                .country(country)
                .build();

        given(cityRepository.getOne(anyInt())).willReturn(entity);

        try {
            CityDto dto = cityService.getByID(anyInt());

            int id = dto.getId();
            assertEquals(1, id);
            assertEquals("A", dto.getName());
            assertEquals(true, dto.getActive());
        } catch (Exception e) {
            Assert.fail();
        }
    }
    //endregion

    //region deleteByID()
    @Test
    public void deleteByID_WhenEmpty_ShouldReturnException() {
        doThrow(EmptyResultDataAccessException.class).when(cityRepository).delete(anyInt());

        try {
            cityService.deleteByID(anyInt());
            Assert.fail();
        } catch (Exception e) {
            assertEquals(EmptyResultDataAccessException.class, e.getClass());
        }
    }

    @Test
    public void deleteByID_WhenExistDistrict_ShouldReturnException() {
        given(districtRepository.countByCity_Id(anyInt())).willReturn(10);

        try {
            cityService.deleteByID(anyInt());
            Assert.fail();
        } catch (Exception e) {
            assertEquals(CityServiceImpl.CITY_SHOULD_NOT_HAVE_DISTRICT, e.getMessage());
        }
    }

    @Test
    public void deleteByID_WhenFull_ShouldDeleteSuccess() {
        try {
            cityService.deleteByID(anyInt());
        } catch (Exception e) {
            Assert.fail();
        }
    }
    //endregion

    //region saveOrUpdate()
    @Test
    public void saveOrUpdate_WhenDtoEmpty_ShouldReturnException() {
        try {
            cityService.saveOrUpdate(null);
            Assert.fail();
        } catch (Exception e) {
            assertEquals(CityServiceImpl.PARAMETERS_MUST_BE_NOT_NULL, e.getMessage());
        }
    }

    @Test
    public void saveOrUpdate_WhenDtoNameEmpty_ShouldReturnException() {
        try {
            cityService.saveOrUpdate(CityDto.getBuilder().build());
            Assert.fail();
        } catch (Exception e) {
            assertEquals(CityServiceImpl.CITY_NAME_MUST_BE_NOT_NULL, e.getMessage());
        }
    }

    @Test
    public void saveOrUpdate_WhenDtoFull_ShouldReturnEntityID() {
        City entity = new City();
        Country country = Country.getBuilder()
                .id(1)
                .name("China")
                .active(true)
                .build();

        entity = City.getBuilder()
                .id(1)
                .name("A")
                .active(true)
                .country(country)
                .build();

        given(cityRepository.save(any(City.class))).willReturn(entity);

        try {

            CountryDto countryDto = CountryDto.getBuilder()
                    .id(1)
                    .name("China")
                    .active(true)
                    .build();

            CityDto dto = CityDto.getBuilder()
                    .id(1)
                    .name("A")
                    .active(true)
                    .countryDto(countryDto)
                    .build();

            int entityID = cityService.saveOrUpdate(dto);
            assertEquals(1,entityID);
        } catch (Exception e) {
            Assert.fail();
        }
    }
    //endregion
}
