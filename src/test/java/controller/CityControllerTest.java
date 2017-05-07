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

import config.CityControllerTestConfig;
import config.SpringWebContext;
import dto.CityDto;
import dto.CountryDto;

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
import service.CityService;

/**
 * Created by okan on 6.05.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CityControllerTestConfig.class, SpringWebContext.class})
@WebAppConfiguration
public class CityControllerTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(CountryControllerTest.class);

  @Autowired
  private CityService cityService;

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setUp() {
    Mockito.reset(cityService);
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void showAddCityForm_ShouldCreateFormObjectAndRenderAddCityForm()
      throws Exception {
    ResultActions result = mockMvc.perform(get(CityController.REQUEST_MAPPING_CITY))
        //.andDo(print())  //Gelen sonucu konsola basar.
        .andExpect(status().isOk())
        .andExpect(view().name(CityController.VIEW_CITY_ADD))
        .andExpect(model().size(2))
        .andExpect(model().hasNoErrors());

    MockHttpServletResponse response = result.andReturn().getResponse();
    assertTrue(50000 > response.getContentAsByteArray().length);

    Map<String, Object> model = result.andReturn().getModelAndView().getModel();
    CityDto cityDto = (CityDto) model.get("cityDto");
    assertNull(cityDto.getId());
    assertNull(cityDto.getName());
    assertNull(cityDto.getCode());
    assertNull(cityDto.getBig());

    LOGGER.error("******" + cityDto.getName());

    verifyZeroInteractions(cityService);
  }

  @Test
  public void add_NewCityEntry_ShouldAddCityEntryAndRenderViewCityEntryView()
      throws Exception {

    when(cityService.saveOrUpdate(any(CityDto.class))).thenReturn(1);

    ResultActions result = mockMvc.perform(post(CityController.REQUEST_MAPPING_CITY)
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("name", "Sehir1")
        .param("code", "Ct1")
        .param("Active", "true")
        .param("_Active", "on")
        .param("Big", "true")
        .param("_Big", "on")
        .param("countryDto.id","1")

    )
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/city/" + 1))
        .andExpect(redirectedUrl("/city/" + 1));

    MockHttpServletResponse response = result.andReturn().getResponse();
    assertTrue(50000 > response.getContentAsByteArray().length);

    //Map<String, Object> model = result.andReturn().getModelAndView().getModel();
    //assertEquals(0, model.size());

    ArgumentCaptor<CityDto> formObjectArgument = ArgumentCaptor.forClass(CityDto.class);
    verify(cityService, times(1)).saveOrUpdate(formObjectArgument.capture());
    verifyNoMoreInteractions(cityService);

    CityDto formObject = formObjectArgument.getValue();
    assertThat(formObject.getName(), is("Sehir1"));
    assertThat(formObject.getCode(), is("Ct1"));
    assertTrue(formObject.getActive());
    assertTrue(formObject.getBig());
  }

  @Test
  public void showDetailCityEntry_ShouldGetObjectAndRenderDetailCityEntry()
      throws Exception {

    CountryDto countryDto = CountryDto.getBuilder()
        .id(2)
        .name("Ülke2")
        .enName("Country2")
        .code("UL2")
        .active(true)
        .build();

    CityDto cityDto = CityDto.getBuilder()
        .countryDto(countryDto)
        .id(1)
        .name("Sehir1")
        .code("Sh1")
        .active(true)
        .big(true)
        .build();

    when(cityService.getById(1)).thenReturn(cityDto);

    ResultActions result = mockMvc.perform(get(CityController.REQUEST_MAPPING_CITY_DETAIL, 1))
        .andDo(print())  //Gelen sonucu konsola basar.
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.TEXT_HTML.toString() + ";charset=ISO-8859-1"))
        .andExpect(view().name(CityController.VIEW_CITY_DETAIL))
        .andExpect(model().size(1))
        .andExpect(model().hasNoErrors());

    MockHttpServletResponse response = result.andReturn().getResponse();
    assertTrue(50000 > response.getContentAsByteArray().length);

    Map<String, Object> model = result.andReturn().getModelAndView().getModel();
    CityDto cityDto2 = (CityDto) model.get(CityController.MODEL_ATTRIBUTE_CITY);
    assertEquals(cityDto.getId(), cityDto2.getId());
    assertEquals(cityDto.getName(), cityDto2.getName());
    assertEquals(cityDto.getCode(), cityDto2.getCode());
    assertTrue(cityDto.getActive());
    assertTrue(cityDto.getBig());

    verify(cityService, times(1)).getById(1);

    verifyNoMoreInteractions(cityService);

  }

  @Test
  public void showJsonDetailCityEntry_ShouldGetObjectAndRenderDetailCityEntry()
      throws Exception {

    CountryDto countryDto = CountryDto.getBuilder()
        .id(2)
        .name("Ülke2")
        .enName("Country2")
        .code("UL2")
        .active(true)
        .build();

    CityDto cityDto = CityDto.getBuilder()
        .countryDto(countryDto)
        .id(1)
        .name("Sehir1")
        .code("Sh1")
        .active(true)
        .big(true)
        .build();

    when(cityService.getById(1)).thenReturn(cityDto);

    ResultActions result = mockMvc.perform(
        get(CityController.REQUEST_MAPPING_CITY_DETAIL + ".json", 1))
        .andDo(print())  //Gelen sonucu konsola basar.
        .andExpect(status().isOk())
        .andExpect(view().name(CityController.VIEW_CITY_DETAIL))
        .andExpect(model().size(1))
        .andExpect(model().hasNoErrors())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8.toString()))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("Sehir1")))
        .andExpect(jsonPath("$.code", is("Sh1")))
        .andExpect(jsonPath("$.active", is(true)))
        .andExpect(jsonPath("$.big", is(true)));

    MockHttpServletResponse response = result.andReturn().getResponse();
    assertTrue(50000 > response.getContentAsByteArray().length);

    Map<String, Object> model = result.andReturn().getModelAndView().getModel();
    CityDto cityDto2 = (CityDto) model.get(CityController.MODEL_ATTRIBUTE_CITY);
    assertEquals(cityDto.getId(), cityDto2.getId());
    assertEquals(cityDto.getName(), cityDto2.getName());
    assertEquals(cityDto.getCode(), cityDto2.getCode());
    assertTrue(cityDto.getActive());
    assertTrue(cityDto.getBig());

    verify(cityService, times(1)).getById(1);

    verifyNoMoreInteractions(cityService);

  }

}
