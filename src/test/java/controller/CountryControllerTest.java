package controller;

import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import config.CountryControllerTestConfig;
import config.SpringWebContext;
import dto.CountryDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import service.CountryService;


/**
 * Created by Coskun on 25.3.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CountryControllerTestConfig.class, SpringWebContext.class})
@WebAppConfiguration
public class CountryControllerTest {

  //  @Autowired
  //  private CountryService countryService;

  //private MockMvc mockMvc;

  //  @Autowired
  //  private WebApplicationContext webApplicationContext;

  //  @Before
  //  public void setUp() {
  //    Mockito.reset(countryService);
  //    //mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  //  }

  //  @Test
  //  public void showAddCountryForm_ShouldCreateFormObjectAndRenderAddCountryForm()
  // throws Exception {
  //    mockMvc.perform(get(CountryController.REQUEST_MAPPING_COUNTRY))
  //        .andExpect(status().isOk())
  //        .andExpect(view().name(CountryController.VIEW_COUNTRY_ADD))
  //        .andExpect(forwardedUrl("/WEB-INF/views/country/add.html"));
  //
  //    verifyZeroInteractions(countryService);
  //  }


}
