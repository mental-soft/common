package service;

import config.JobServiceTestConfig;
import dto.JobListDto;
import entity.Job;
import jpa.JobRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;

/**
 * Created by Nyomoto on 11.2.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JobServiceTestConfig.class})
public class JobServiceTest {

    //TODO Tecnical Depth1: Tekrar eden kodlar düzenlenecek.

    @Autowired
    JobRepository repository;

    @Autowired
    JobService service;

    @Before
    public  void before() {
        reset(repository);
    }

    @Test
    public void service_IsNotNull() {
        assertNotNull(service);
    }

    //region getAll()
    @Test
    public void getAll_WhenEmpty_ShouldReturnSize() {
        given(repository.findAll()).willReturn(new ArrayList<>());
        //when(repository.findAll()).thenReturn(new ArrayList<>());

        List<JobListDto> list = service.getAll();

        assertEquals(0, list.size());
    }

    @Test
    public void getAll_WhenFull_ShouldReturnSize() {
        List<Job> entityList = new ArrayList<>();
        entityList.add(Job.getBuilder()
                .id(1)
                .name("A")
                .active(true)
                .build());

        entityList.add(Job.getBuilder()
                .id(2)
                .name("B")
                .active(true)
                .build());

        entityList.add(Job.getBuilder()
                .id(3)
                .name("C")
                .active(true)
                .build());

        given(repository.findAll()).willReturn(entityList);

        List<JobListDto> list = service.getAll();

        assertEquals(3, list.size());
    }

    @Test
    public void getAll_WhenFull_ShouldReturnInfo() {
        List<Job> entityList = new ArrayList<>();
        entityList.add(Job.getBuilder()
                .id(1)
                .name("A")
                .active(true)
                .build());

        entityList.add(Job.getBuilder()
                .id(2)
                .name("B")
                .active(true)
                .build());

        entityList.add(Job.getBuilder()
                .id(3)
                .name("C")
                .active(true)
                .build());

        given(repository.findAll()).willReturn(entityList);

        JobListDto dto = service.getAll().get(0);

        assertEquals(1, dto.getId());
        assertEquals("A", dto.getName());
        assertEquals(true, dto.getActive());
    }
    //endregion()

    //region getByID()
    @Test
    public void getByID_WhenEmpty_ShouldReturnException() {
        given(repository.getOne(anyInt())).willReturn(null);

        try {
            service.getByID(anyInt());
            Assert.fail();
        } catch (Exception e) {
            assertEquals(JobServiceImpl.NOT_FOUND_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void getByID_WhenFull_ShouldReturnInfo() {
        Job entity = new Job();
        entity = Job.getBuilder()
                .id(1)
                .name("A")
                .active(true)
                .build();

        given(repository.getOne(anyInt())).willReturn(entity);

        try {
            JobListDto dto = service.getByID(anyInt());
            assertEquals(1, dto.getId());
            assertEquals("A", dto.getName());
            assertEquals(true, dto.getActive());
        } catch (Exception e) {
            Assert.fail();;
        }
    }
    //endregion

    //region deleteByID()
    @Test
    public void deleteByID_WhenEmpty_ShouldReturnException() {
        doThrow(EmptyResultDataAccessException.class).when(repository).delete(anyInt());

        try {
            service.deleteByID(anyInt());
            Assert.fail();
        } catch (Exception e) {
            assertEquals(EmptyResultDataAccessException.class, e.getClass());
        }
    }

    @Test
    public void deleteByID_WhenFull_ShouldDeleteSuccess() {
        try {
            service.deleteByID(anyInt());
        } catch (Exception e) {
            Assert.fail();
        }
    }
    //endregion


}
