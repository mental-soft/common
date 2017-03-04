package service;

import config.PersistenceContext;
import config.PersistenceContextTest;
import config.ServiceContext;
import dto.CountryDto;
import entity.Country;
import jpa.CityRepository;
import jpa.CountryRepository;
import jpa.DistrictRepository;
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


/**
 * Created by okan on 18.02.2017.
 */
@TestPropertySource({"classpath:application.properties",
        "classpath:environment/developer.properties"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class, ServiceContext.class})
public class DistrictServiceTest {
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    CountryService countryService;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    CityService cityService;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    DistrictService districtService;

    @Test
    public void isNullDistrictService(){
        assertNotNull(districtService);
    }

}
