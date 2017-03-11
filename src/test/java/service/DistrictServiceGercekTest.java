package service;

import config.DistrictServiceTestConfig;
import dto.CityDto;
import dto.DistrictDto;
import entity.City;
import entity.District;
import jpa.DistrictRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
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

@TestPropertySource({"classpath:application.properties",
        "classpath:environment/developer.properties"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {config.PersistenceContext.class, config.ServiceContext.class})
public class DistrictServiceGercekTest {

    //TODO Tecnical Depth1: Tekrar eden kodlar düzenlenecek.

    @Autowired
    DistrictRepository repository;

    @Autowired
    DistrictService service;



    @Test
    public void service_IsNotNull() {
        assertNotNull(service);
    }

    //region getAll()
    @Test
    public void getAll_WhenEmpty_ShouldReturnSize() {

        //when(repository.findAll()).thenReturn(new ArrayList<>());

        List<DistrictDto> list = service.getAll();

        assertEquals(5, list.size());
    }


    //endregion()


}
