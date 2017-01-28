package jpa;

import config.PersistenceContext;
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
        "classpath:environment/qa.properties"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
public class CountryRepositoryTestQa {

    @Autowired
    public CountryRepository countryRepository;

    @Test
    public void countryRepository_IsNotNull() {
        Assert.assertNotNull(countryRepository);
    }

    @Test
    public void count_EntrySizeFound() {
        long size = countryRepository.count();
        Assert.assertEquals(0, size);
    }

}
