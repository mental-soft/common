package service;

import config.BloodGroupServiceTestConfig;
import dto.BloodGroupListDto;
import entity.BloodGroup;
import jpa.BloodGroupRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;

/**
 * Created by Nyomoto on 11.2.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BloodGroupServiceTestConfig.class})
public class BloodGroupServiceTest {

    //TODO Tecnical Depth1: Tekrar eden kodlar düzenlenecek.

    @Autowired
    BloodGroupRepository repository;

    @Autowired
    BloodGroupService service;

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

        List<BloodGroupListDto> list = service.getAll();

        assertEquals(0, list.size());
    }

    @Test
    public void getAll_WhenFull_ShouldReturnSize() {
        List<BloodGroup> entityList = new ArrayList<>();
        entityList.add(BloodGroup.getBuilder()
                .id(1)
                .name("A")
                .active(true)
                .build());

        entityList.add(BloodGroup.getBuilder()
                .id(2)
                .name("B")
                .active(true)
                .build());

        entityList.add(BloodGroup.getBuilder()
                .id(3)
                .name("C")
                .active(true)
                .build());

        given(repository.findAll()).willReturn(entityList);

        List<BloodGroupListDto> list = service.getAll();

        assertEquals(3, list.size());
    }

    @Test
    public void getAll_WhenFull_ShouldReturnInfo() {
        List<BloodGroup> entityList = new ArrayList<>();
        entityList.add(BloodGroup.getBuilder()
                .id(1)
                .name("A")
                .active(true)
                .build());

        entityList.add(BloodGroup.getBuilder()
                .id(2)
                .name("B")
                .active(true)
                .build());

        entityList.add(BloodGroup.getBuilder()
                .id(3)
                .name("C")
                .active(true)
                .build());

        given(repository.findAll()).willReturn(entityList);

        BloodGroupListDto dto = service.getAll().get(0);

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
            assertEquals(BloodGroupServiceImpl.NOT_FOUND_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void getByID_WhenFull_ShouldReturnInfo() {
        BloodGroup entity = new BloodGroup();
        entity = BloodGroup.getBuilder()
                .id(1)
                .name("A")
                .active(true)
                .build();

        given(repository.getOne(anyInt())).willReturn(entity);

        try {
            BloodGroupListDto dto = service.getByID(anyInt());
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

    //region saveOrUpdate()
    @Test
    public void saveOrUpdate_WhenDtoEmpty_ShouldReturnException() {
        try {
            service.saveOrUpdate(null);
            Assert.fail();
        } catch (Exception e) {
            assertEquals(BloodGroupServiceImpl.PARAMETERS_MUST_BE_NOT_NULL, e.getMessage());
        }
    }

    @Test
    public void saveOrUpdate_WhenDtoNameEmpty_ShouldReturnException() {
        try {
            service.saveOrUpdate(BloodGroupListDto.getBuilder().build());
            Assert.fail();
        } catch (Exception e) {
            assertEquals(BloodGroupServiceImpl.BLOODGROUP_NAME_MUST_BE_NOT_NULL, e.getMessage());
        }
    }

    @Test
    public void saveOrUpdate_WhenDtoFull_ShouldReturnEntityID() {
        BloodGroup entity = new BloodGroup();
        entity = BloodGroup.getBuilder()
                .id(1)
                .name("A")
                .active(true)
                .build();

        given(repository.save(any(BloodGroup.class))).willReturn(entity);

        try {
            BloodGroupListDto dto = BloodGroupListDto.getBuilder()
                    .id(1)
                    .name("A")
                    .active(true)
                    .build();

            int entityID = service.saveOrUpdate(dto);
            assertEquals(1, entityID);
        } catch (Exception e) {
            Assert.fail();;
        }
    }
    //endregion
}
