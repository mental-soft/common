package jpa;

import config.PersistenceContext;
import entity.City;
import entity.Country;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resources;

/**
 * Created by Coşkun on 21.1.2017.
 */
@TestPropertySource({"classpath:application.properties",
        "classpath:environment/developer.properties"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
public class CountryRepositoryTest {

    @Autowired
    public CountryRepository countryRepository;

    @Autowired
    public CityRepository cityRepository;

    @Test
    public void countryRepository_IsNotNull() {
        Assert.assertNotNull(countryRepository);
    }

    @Test
    public void findOne_EntryFound() {
        Country country = countryRepository.findOne(1);
        Assert.assertEquals("TURKEY", country.getEnName());
    }

    @Transactional
    public void insert_Entry() {
        Country country = new Country();
        country.setName("Test556");
        country.setCode("T556");
        country.setActive(true);

        country = countryRepository.save(country);

        City newCity = new City();
        newCity.setName("123");
        newCity.setCountry(country);

        cityRepository.save(newCity);

        newCity = new City();
        newCity.setName("sd33afsd");
        newCity.setCountry(country);

        cityRepository.save(newCity);

        newCity = new City();
        newCity.setName("sd444afsd");
        newCity.setCountry(country);

        cityRepository.save(newCity);
    }

    @Test
    public void testInsertEntry() {
        try {
            insert_Entry();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insert_EntryTest() {
        Country country = new Country();
        country.setName("Test233333");
        country.setCode("T2333333");
        country.setActive(true);

        City newCity = new City();
        newCity.setName("asdfsdf");
        newCity.setCode("22");
        newCity.setCountry(country);
        newCity.setActive(true);
        newCity.setBig(true);

        country.getCities().add(newCity);

        newCity = new City();
        newCity.setName("333");
        newCity.setCode("33");
        newCity.setCountry(country);
        newCity.setActive(true);
        newCity.setBig(true);

        country.getCities().add(newCity);
        newCity = new City();
        newCity.setName("4444");
        newCity.setCode("44");
        newCity.setCountry(country);
        newCity.setActive(true);
        newCity.setBig(true);

        country.getCities().add(newCity);


        countryRepository.save(country);

    }

}
