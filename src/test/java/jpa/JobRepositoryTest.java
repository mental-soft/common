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
        "classpath:environment/developer.properties"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
public class JobRepositoryTest {

    @Autowired
    public JobRepository jobRepository;

    @Test
    public void CityRepository_IsNotNull() {
        Assert.assertNotNull(jobRepository);
    }

    @Test
    public void count_EntrySizeFound() {
        long size = jobRepository.count();
        Assert.assertEquals(5, size);
    }

}
