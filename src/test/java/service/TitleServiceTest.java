package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;

import config.TitleServiceTestConfig;
import dto.TitleListDto;
import entity.Title;

import java.util.ArrayList;
import java.util.List;

import jpa.TitleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Nyomoto on 11.2.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TitleServiceTestConfig.class})
public class TitleServiceTest {

  //TODO Tecnical Depth1: Tekrar eden kodlar düzenlenecek.

  @Autowired
  TitleRepository repository;

  @Autowired
  TitleService service;

  @Before
  public void before() {
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

    List<TitleListDto> list = service.getAll();

    assertEquals(0, list.size());
  }

  @Test
  public void getAll_WhenFull_ShouldReturnSize() {
    List<Title> entityList = new ArrayList<>();
    entityList.add(Title.getBuilder()
        .id(1)
        .name("A")
        .active(true)
        .build());

    entityList.add(Title.getBuilder()
        .id(2)
        .name("B")
        .active(true)
        .build());

    entityList.add(Title.getBuilder()
        .id(3)
        .name("C")
        .active(true)
        .build());

    given(repository.findAll()).willReturn(entityList);

    List<TitleListDto> list = service.getAll();

    assertEquals(3, list.size());
  }

  @Test
  public void getAll_WhenFull_ShouldReturnInfo() {
    List<Title> entityList = new ArrayList<>();
    entityList.add(Title.getBuilder()
        .id(1)
        .name("A")
        .active(true)
        .build());

    entityList.add(Title.getBuilder()
        .id(2)
        .name("B")
        .active(true)
        .build());

    entityList.add(Title.getBuilder()
        .id(3)
        .name("C")
        .active(true)
        .build());

    given(repository.findAll()).willReturn(entityList);

    TitleListDto dto = service.getAll().get(0);

    assertEquals(1, dto.getId());
    assertEquals("A", dto.getName());
    assertEquals(true, dto.getActive());
  }
  //endregion()

  //region getById()
  @Test
  public void getById_WhenEmpty_ShouldReturnException() {
    given(repository.getOne(anyInt())).willReturn(null);

    try {
      service.getById(anyInt());
      Assert.fail();
    } catch (Exception e) {
      assertEquals(TitleServiceImpl.NOT_FOUND_MESSAGE, e.getMessage());
    }
  }

  @Test
  public void getById_WhenFull_ShouldReturnInfo() {
    Title entity = new Title();
    entity = Title.getBuilder()
        .id(1)
        .name("A")
        .active(true)
        .build();

    given(repository.getOne(anyInt())).willReturn(entity);

    try {
      TitleListDto dto = service.getById(anyInt());
      assertEquals(1, dto.getId());
      assertEquals("A", dto.getName());
      assertEquals(true, dto.getActive());
    } catch (Exception e) {
      Assert.fail();
      ;
    }
  }
  //endregion

  //region deleteById()
  @Test
  public void deleteById_WhenEmpty_ShouldReturnException() {
    doThrow(EmptyResultDataAccessException.class).when(repository).delete(anyInt());

    try {
      service.deleteById(anyInt());
      Assert.fail();
    } catch (Exception e) {
      assertEquals(EmptyResultDataAccessException.class, e.getClass());
    }
  }

  @Test
  public void deleteById_WhenFull_ShouldDeleteSuccess() {
    try {
      service.deleteById(anyInt());
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
      assertEquals(TitleServiceImpl.PARAMETERS_MUST_BE_NOT_NULL, e.getMessage());
    }
  }

  @Test
  public void saveOrUpdate_WhenDtoNameEmpty_ShouldReturnException() {
    try {
      service.saveOrUpdate(TitleListDto.getBuilder().build());
      Assert.fail();
    } catch (Exception e) {
      assertEquals(TitleServiceImpl.TITLE_NAME_MUST_BE_NOT_NULL, e.getMessage());
    }
  }

  @Test
  public void saveOrUpdate_WhenDtoFull_ShouldReturnEntityId() {
    Title entity = new Title();
    entity = Title.getBuilder()
        .id(1)
        .name("A")
        .active(true)
        .build();

    given(repository.save(any(Title.class))).willReturn(entity);

    try {
      TitleListDto dto = TitleListDto.getBuilder()
          .id(1)
          .name("A")
          .active(true)
          .build();

      int entityId = service.saveOrUpdate(dto);
      assertEquals(1, entityId);
    } catch (Exception e) {
      Assert.fail();
      ;
    }
  }
  //endregion
}
