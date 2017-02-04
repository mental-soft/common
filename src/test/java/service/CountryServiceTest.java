package service;

import config.PersistenceContext;
import config.PersistenceContextTest;
import config.ServiceContext;
import jpa.CountryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Coşkun on 4.2.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContextTest.class, ServiceContext.class})
public class CountryServiceTest {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CountryService countryService;

    @Before
    public void setUp() {
        when(countryRepository.count()).thenReturn(10L);
    }

    @Test
    public void countryService_IsNotNull() {
        assertNotNull(countryService);
    }

    @Test
    public void countryActiveCount_ShouldReturn10() {
        assertEquals(10, countryService.countryActiveCount());
    }

}
