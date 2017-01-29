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

    @Test
    public void countryRepository_IsNotNull() {
        Assert.assertNotNull(countryRepository);
    }

    @Test
    public void count_EntrySizeFound() {
        long size = countryRepository.count();
        Assert.assertEquals(5, size);
    }

    @Test
    public void findOne_EntryFound() {
        Country country = countryRepository.findOne(1);
        Assert.assertEquals("TURKEY", country.getEnName());
    }

    @Test
    public void insert_Entry() {
        Country country = new Country();
        country.setName("Test2");
        country.setCode("T2");
        country.setActive(true);

        country = countryRepository.save(country);
    }

}
