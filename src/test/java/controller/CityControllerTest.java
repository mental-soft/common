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


}
