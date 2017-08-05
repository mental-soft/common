package com.teammental.common.web.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import com.teammental.common.bll.dto.IdNameDto;
import com.teammental.common.bll.service.CommonService;
import com.teammental.common.dal.entity.City;
import com.teammental.common.util.TestDataGenerator;
import com.teammental.common.util.TestUtil;
import com.teammental.memapper.MeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CityController.class)
public class CityControllerTest {

  @MockBean
  private CommonService commonService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnOkAndCities_whenCitiesFound() throws Exception {
    final int citySize = 2;
    List<City> cities = TestDataGenerator.prepareRandomListOfCities(citySize);
    Optional<List<IdNameDto>> expectedDtosOptional = MeMapper.getMapperFromList(cities)
        .mapToList(IdNameDto.class);
    List<IdNameDto> expectedDtos = expectedDtosOptional.get();

    when(commonService.getCities())
        .thenReturn(expectedDtos);

    mockMvc.perform(get("/cities"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$", hasSize(citySize)))
        .andExpect(jsonPath("$[0].id", is(expectedDtos.get(0).getId())))
        .andExpect(jsonPath("$[0].name", is(expectedDtos.get(0).getName())))
        .andExpect(jsonPath("$[1].id", is(expectedDtos.get(1).getId())))
        .andExpect(jsonPath("$[1].name", is(expectedDtos.get(1).getName())));

    verify(commonService, times(1)).getCities();
  }
}