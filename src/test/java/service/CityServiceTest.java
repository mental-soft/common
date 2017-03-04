package service;

import config.PersistenceContext;
import config.PersistenceContextTest;
import config.ServiceContext;
import dto.CountryDto;
import entity.Country;
import jpa.CityRepository;
import jpa.CountryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@TestPropertySource({"classpath:application.properties",
        "classpath:environment/developer.properties"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class, ServiceContext.class})

/**
 * Created by okan on 17.02.2017.
 */
public class CityServiceTest {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CountryService countryService;

    @Autowired
    CountryRepository cityRepository;

    @Autowired
    CountryService cityService;

    @Test
    public void isNullCityService(){
        assertNotNull(cityService);
    }
}
