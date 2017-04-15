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

import config.CountryControllerTestConfig;
import config.SpringWebContext;
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
import service.CountryService;


/**
 * Created by Coskun on 25.3.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CountryControllerTestConfig.class, SpringWebContext.class})
@WebAppConfiguration
public class CountryControllerTest {

  @Autowired
  private CountryService countryService;

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setUp() {
    Mockito.reset(countryService);
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void showAddCountryForm_ShouldCreateFormObjectAndRenderAddCountryForm()
      throws Exception {
    ResultActions result = mockMvc.perform(get(CountryController.REQUEST_MAPPING_COUNTRY))
        //.andDo(print())  //Gelen sonucu konsola basar.
        .andExpect(status().isOk())
        .andExpect(view().name(CountryController.VIEW_COUNTRY_ADD))
        .andExpect(model().size(1))
        .andExpect(model().hasNoErrors());

    MockHttpServletResponse response = result.andReturn().getResponse();
    assertTrue(50000 > response.getContentAsByteArray().length);

    Map<String, Object> model = result.andReturn().getModelAndView().getModel();
    CountryDto countryDto = (CountryDto) model.get("countryDto");
    assertNull(countryDto.getId());
    assertNull(countryDto.getName());
    assertNull(countryDto.getCode());
    assertNull(countryDto.getEnName());
    assertNull(countryDto.getIsActive());

    verifyZeroInteractions(countryService);
  }

  @Test
  public void add_NewCountryEntry_ShouldAddCountryEntryAndRenderViewCountryEntryView()
      throws Exception {

    when(countryService.saveOrUpdate(any(CountryDto.class))).thenReturn(1);

    ResultActions result = mockMvc.perform(post(CountryController.REQUEST_MAPPING_COUNTRY)
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("name", "Ülke1")
        .param("enName", "Country1")
        .param("code", "UL")
        .param("isActive", "true")
        .param("_isActive", "on")
    )
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/country/" + 1))
        .andExpect(redirectedUrl("/country/" + 1));

    MockHttpServletResponse response = result.andReturn().getResponse();
    assertTrue(50000 > response.getContentAsByteArray().length);

    //Map<String, Object> model = result.andReturn().getModelAndView().getModel();
    //assertEquals(0, model.size());

    ArgumentCaptor<CountryDto> formObjectArgument = ArgumentCaptor.forClass(CountryDto.class);
    verify(countryService, times(1)).saveOrUpdate(formObjectArgument.capture());
    verifyNoMoreInteractions(countryService);

    CountryDto formObject = formObjectArgument.getValue();
    assertThat(formObject.getName(), is("Ülke1"));
    assertThat(formObject.getEnName(), is("Country1"));
    assertThat(formObject.getCode(), is("UL"));
    assertTrue(formObject.getIsActive());
  }

  @Test
  public void showDetailCountryEntry_ShouldGetObjectAndRenderDetailCountryEntry()
      throws Exception {

    CountryDto dto = CountryDto.getBuilder()
        .id(2)
        .name("Ülke2")
        .enName("Country2")
        .code("UL2")
        .active(true)
        .build();

    when(countryService.getById(1)).thenReturn(dto);

    ResultActions result = mockMvc.perform(get(CountryController.REQUEST_MAPPING_COUNTRY_DETAIL, 1))
        .andDo(print())  //Gelen sonucu konsola basar.
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.TEXT_HTML.toString() + ";charset=ISO-8859-1"))
        .andExpect(view().name(CountryController.VIEW_COUNTRY_DETAIL))
        .andExpect(model().size(1))
        .andExpect(model().hasNoErrors());

    MockHttpServletResponse response = result.andReturn().getResponse();
    assertTrue(50000 > response.getContentAsByteArray().length);

    Map<String, Object> model = result.andReturn().getModelAndView().getModel();
    CountryDto countryDto = (CountryDto) model.get(CountryController.MODEL_ATTRIBUTE_COUNTRY);
    assertEquals(dto.getId(), countryDto.getId());
    assertEquals(dto.getName(), countryDto.getName());
    assertEquals(dto.getCode(), countryDto.getCode());
    assertEquals(dto.getEnName(), countryDto.getEnName());
    assertTrue(countryDto.getIsActive());

    verify(countryService, times(1)).getById(1);

    verifyNoMoreInteractions(countryService);

  }

  @Test
  public void showJsonDetailCountryEntry_ShouldGetObjectAndRenderDetailCountryEntry()
      throws Exception {

    CountryDto dto = CountryDto.getBuilder()
        .id(2)
        .name("Ülke2")
        .enName("Country2")
        .code("UL2")
        .active(true)
        .build();

    when(countryService.getById(2)).thenReturn(dto);

    ResultActions result = mockMvc.perform(
        get(CountryController.REQUEST_MAPPING_COUNTRY_DETAIL + ".json", 2))
        .andDo(print())  //Gelen sonucu konsola basar.
        .andExpect(status().isOk())
        .andExpect(view().name(CountryController.VIEW_COUNTRY_DETAIL))
        .andExpect(model().size(1))
        .andExpect(model().hasNoErrors())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8.toString()))
        .andExpect(jsonPath("$.id", is(2)))
        .andExpect(jsonPath("$.name", is("Ülke2")))
        .andExpect(jsonPath("$.enName", is("Country2")))
        .andExpect(jsonPath("$.code", is("UL2")))
        .andExpect(jsonPath("$.isActive", is(true)));

    MockHttpServletResponse response = result.andReturn().getResponse();
    assertTrue(50000 > response.getContentAsByteArray().length);

    Map<String, Object> model = result.andReturn().getModelAndView().getModel();
    CountryDto countryDto = (CountryDto) model.get(CountryController.MODEL_ATTRIBUTE_COUNTRY);
    assertEquals(dto.getId(), countryDto.getId());
    assertEquals(dto.getName(), countryDto.getName());
    assertEquals(dto.getCode(), countryDto.getCode());
    assertEquals(dto.getEnName(), countryDto.getEnName());
    assertTrue(countryDto.getIsActive());

    verify(countryService, times(1)).getById(2);

    verifyNoMoreInteractions(countryService);

  }

}
