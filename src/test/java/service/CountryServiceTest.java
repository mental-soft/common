package service;

import config.CountryServiceTestConfig;
import dto.CountryDto;
import entity.Country;
import jpa.CityRepository;
import jpa.CountryRepository;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CountryServiceTestConfig.class})
public class CountryServiceTest {

    //TODO Tecnical Depth1: Tekrar eden kodlar düzenlenecek.

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CountryService service;

    @Autowired
    CityService cityservice;

    @Autowired
    CityRepository cityRepository;

    @Before
    public  void before() {
        reset(countryRepository);
    }

    @Test
    public void service_IsNotNull() {
        assertNotNull(service);
    }

    //region getAll()
    @Test
    public void getAll_WhenEmpty_ShouldReturnSize() {
        given(countryRepository.findAll()).willReturn(new ArrayList<>());
        //when(repository.findAll()).thenReturn(new ArrayList<>());

        List<CountryDto> list = service.getAll();

        assertEquals(0, list.size());
    }

    @Test
    public void getAll_WhenFull_ShouldReturnSize() {
        List<Country> entityList = new ArrayList<>();
        entityList.add(Country.getBuilder()
                .id(1)
                .name("China")
                .active(true)
                .build());

        entityList.add(Country.getBuilder()
                .id(2)
                .name("Korea")
                .active(true)
                .build());

        entityList.add(Country.getBuilder()
                .id(3)
                .name("Australia")
                .active(true)
                .build());

        given(countryRepository.findAll()).willReturn(entityList);

        List<CountryDto> list = service.getAll();

        assertEquals(3, list.size());
    }

    @Test
    public void getAll_WhenFull_ShouldReturnInfo() {
        List<Country> entityList = new ArrayList<>();
        entityList.add(Country.getBuilder()
                .id(1)
                .name("China")
                .active(true)
                .build());

        entityList.add(Country.getBuilder()
                .id(2)
                .name("Korea")
                .active(true)
                .build());

        entityList.add(Country.getBuilder()
                .id(3)
                .name("Australia")
                .active(true)
                .build());

        given(countryRepository.findAll()).willReturn(entityList);

        CountryDto dto = service.getAll().get(0);
        int id = dto.getId();
        assertEquals(1, id);
        assertEquals("China", dto.getName());
        assertEquals(true, dto.getIsActive());
    }
    //endregion()

    //region getByID()
    @Test
    public void getByID_WhenEmpty_ShouldReturnException() {
        given(countryRepository.getOne(anyInt())).willReturn(null);

        try {
            service.getByID(anyInt());
            Assert.fail();
        } catch (Exception e) {
            assertEquals(CountryServiceImpl.NOT_FOUND_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void getByID_WhenFull_ShouldReturnInfo() {
        Country entity = new Country();
        entity = Country.getBuilder()
                .id(1)
                .name("China")
                .active(true)
                .build();

        given(countryRepository.getOne(anyInt())).willReturn(entity);

        try {
            CountryDto dto = service.getByID(anyInt());

            int id = dto.getId();
            assertEquals(1, id);
            assertEquals("China", dto.getName());
            assertEquals(true, dto.getIsActive());
        } catch (Exception e) {
            Assert.fail();
        }
    }
    //endregion

    //region deleteByID()
    @Test
    public void deleteByID_WhenEmpty_ShouldReturnException() {
        doThrow(EmptyResultDataAccessException.class).when(countryRepository).delete(anyInt());

        try {
            service.deleteByID(anyInt());
            Assert.fail();
        } catch (Exception e) {
            assertEquals(EmptyResultDataAccessException.class, e.getClass());
        }
    }

    @Test
    public void deleteByID_WhenExistCity_ShouldReturnException() {
        given(cityRepository.countByCountry_Id(anyInt())).willReturn(10);

        try {
            service.deleteByID(anyInt());
            Assert.fail();
        } catch (Exception e) {
            assertEquals(CountryServiceImpl.COUNTRY_SHOULD_NOT_HAVE_CITY, e.getMessage());
        }
    }

    @Test
    public void deleteByID_WhenFull_ShouldDeleteSuccess() {
        try {
            service.deleteByID(anyInt());
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
            assertEquals(CountryServiceImpl.PARAMETERS_MUST_BE_NOT_NULL, e.getMessage());
        }
    }

    @Test
    public void saveOrUpdate_WhenDtoNameEmpty_ShouldReturnException() {
        try {
            service.saveOrUpdate(CountryDto.getBuilder().build());
            Assert.fail();
        } catch (Exception e) {
            assertEquals(CountryServiceImpl.COUNTRY_NAME_MUST_BE_NOT_NULL, e.getMessage());
        }
    }

    @Test
    public void saveOrUpdate_WhenDtoFull_ShouldReturnEntityID() {
        Country entity = new Country();
        entity = Country.getBuilder()
                .id(1)
                .name("A")
                .active(true)
                .build();

        given(countryRepository.save(any(Country.class))).willReturn(entity);

        try {
            CountryDto dto = CountryDto.getBuilder()
                    .id(1)
                    .name("A")
                    .active(true)
                    .build();

            int entityID = service.saveOrUpdate(dto);
            assertEquals(1,entityID);
        } catch (Exception e) {
            Assert.fail();
        }
    }
    //endregion
}
