package jpa;

import config.PersistenceContext;
import entity.City;
import entity.Country;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Coşkun on 21.1.2017.
 */
@TestPropertySource({"classpath:application.properties",
        "classpath:environment/developer.properties"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
public class CityRepositoryTest {

    @Autowired
    public CityRepository cityRepository;

    @Test
    public void CityRepository_IsNotNull() {
        Assert.assertNotNull(cityRepository);
    }

    @Test
    public void count_EntrySizeFound() {
        long size = cityRepository.count();
        Assert.assertEquals(5, size);
    }

    @Test
    public void findOne_RelationEntryFound() {
        City city = cityRepository.findOne(1);
        Assert.assertNotNull(city.getCountry());
        Assert.assertEquals("TURKEY", city.getCountry().getEnName());
    }

}
