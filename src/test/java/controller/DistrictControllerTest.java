package controller;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import config.DistrictControllerTestConfig;
import config.SpringWebContext;
import dto.CityDto;
import dto.DistrictDto;

import java.nio.charset.Charset;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.WebApplicationContext;
import service.DistrictService;


/**
 * Created by okan on 13.05.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DistrictControllerTestConfig.class, SpringWebContext.class})
@WebAppConfiguration
public class DistrictControllerTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(DistrictControllerTest.class);

  @Autowired
  private DistrictService districtService;

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setUp() {
    Mockito.reset(districtService);
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void showAddDistrictForm_ShouldCreateFormObjectAndRenderAddDistrictForm()
      throws  Exception {
    ResultActions result = mockMvc.perform(get(DistrictController.REQUEST_MAPPING_DISTRICT))
        //.andDo(print())  //Gelen sonucu konsola basar.
        .andExpect(status().isOk())
        .andExpect(view().name(DistrictController.VIEW_DISTRICT_ADD))
        .andExpect(model().size(2))
        .andExpect(model().hasNoErrors());

    MockHttpServletResponse response = result.andReturn().getResponse();
    assertTrue(50000 > response.getContentAsByteArray().length);

    Map<String,Object> model = result.andReturn().getModelAndView().getModel();
    DistrictDto distritDto = (DistrictDto) model.get("districtDto");
    assertNull(distritDto.getId());
    assertNull(distritDto.getName());
    assertNull(distritDto.getActive());

    LOGGER.error("******" + distritDto.getName());

    verifyZeroInteractions(districtService);
  }

  @Test
  public void add_NewDistrictEntry_ShouldAddDistrictEntryAndRenderViewDistrictEntryView()
      throws Exception {

    when(districtService.saveOrUpdate(any(DistrictDto.class))).thenReturn(1);

    ResultActions result = mockMvc.perform(post(DistrictController.REQUEST_MAPPING_DISTRICT)
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("name", "Ilce1")
        .param("Active", "true")
        .param("_Active", "on")
        .param("cityDto.id","1")

    )
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/district/" + 1))
        .andExpect(redirectedUrl("/district/" + 1));

    MockHttpServletResponse response = result.andReturn().getResponse();
    assertTrue(50000 > response.getContentAsByteArray().length);

    ArgumentCaptor<DistrictDto> formObjectArgument = ArgumentCaptor.forClass(DistrictDto.class);
    verify(districtService,times(1)).saveOrUpdate(formObjectArgument.capture());
    verifyNoMoreInteractions(districtService);

    DistrictDto formObject = formObjectArgument.getValue();
    assertThat(formObject.getName(),is("Ilce1"));
    assertTrue(formObject.getActive());
  }

  @Test
  public void showDetailDistrictEntry_ShouldGetObjectAndRenderDetailDistrictEntry()
      throws Exception {

    CityDto cityDto = CityDto.getBuilder()
        .id(1)
        .name("Sehir1")
        .active(true)
        .big(true)
        .code("SH1")
        .build();

    DistrictDto districtDto = DistrictDto.getBuilder()
        .cityDto(cityDto)
        .id(1)
        .name("Ilce1")
        .active(true)
        .build();

    when(districtService.getById(1)).thenReturn(districtDto);

    ResultActions result = mockMvc.perform(
        get(DistrictController.REQUEST_MAPPING_DISTRICT_DETAIL, 1)
    )
        .andDo(print())  //Gelen sonucu konsola basar.
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.TEXT_HTML.toString() + ";charset=ISO-8859-1"))
        .andExpect(view().name(DistrictController.VIEW_DISTRICT_DETAIL))
        .andExpect(model().size(1))
        .andExpect(model().hasNoErrors());

    MockHttpServletResponse response = result.andReturn().getResponse();
    assertTrue(50000 > response.getContentAsByteArray().length);

    Map<String, Object> model = result.andReturn().getModelAndView().getModel();
    DistrictDto districtDto1 = (DistrictDto) model.get(DistrictController.MODEL_ATTRIBUTE_DISTRICT);
    assertEquals(districtDto.getId(),districtDto1.getId());
    assertEquals(districtDto.getActive(),districtDto1.getActive());
    assertEquals(districtDto.getName(),districtDto1.getName());

    verify(districtService,times(1)).getById(1);

    verifyNoMoreInteractions(districtService);

  }

  @Test
  public void showJsonDetailDistrictEntry_ShouldGetObjectAndRenderDetailDistrictEntry()
      throws Exception {

    CityDto cityDto = CityDto.getBuilder()
        .id(1)
        .name("Sehir1")
        .active(true)
        .big(true)
        .code("SH1")
        .build();

    DistrictDto districtDto = DistrictDto.getBuilder()
        .cityDto(cityDto)
        .id(1)
        .name("Ilce1")
        .active(true)
        .build();

    when(districtService.getById(1)).thenReturn(districtDto);

    ResultActions result = mockMvc.perform(
        get(DistrictController.REQUEST_MAPPING_DISTRICT_DETAIL + ".json",1))
        .andDo(print()) //Gelen sonucu console basar.
        .andExpect(status().isOk())
        .andExpect(view().name(DistrictController.VIEW_DISTRICT_DETAIL))
        .andExpect(model().size(1))
        .andExpect(model().hasNoErrors())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8.toString()))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name",is("Ilce1")))
        .andExpect(jsonPath("$.active",is(true)));

    MockHttpServletResponse response = result.andReturn().getResponse();
    assertTrue(50000 > response.getContentAsByteArray().length);

    Map<String,Object> model = result.andReturn().getModelAndView().getModel();
    DistrictDto districtDto1 = (DistrictDto) model.get(DistrictController.MODEL_ATTRIBUTE_DISTRICT);
    assertEquals(districtDto.getId(),districtDto1.getId());
    assertEquals(districtDto.getName(),districtDto1.getName());
    assertTrue(districtDto.getActive());

    verify(districtService, times(1)).getById(1);

    verifyNoMoreInteractions(districtService);
  }

}
