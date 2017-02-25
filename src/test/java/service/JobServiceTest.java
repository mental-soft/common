package service;

import config.JobServiceTestConfig;
import config.ServiceContext;
import dto.JobListDto;
import jpa.JobRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

/**
 * Created by Nyomoto on 11.2.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JobServiceTestConfig.class})
public class JobServiceTest {

    @Autowired
    JobRepository repository;

    @Autowired
    JobService service;

    @Test
    public void service_IsNotNull() {
        assertNotNull(service);
    }

    @Test
    public void findAll_ShouldReturnemptyList() {
        given(repository.findAll()).willReturn(new ArrayList<>());
        //when(repository.findAll()).thenReturn(new ArrayList<>());

        List<JobListDto> list = service.getAll();

        assertEquals(0, list.size());
    }

}
