package com.teammental.common.web.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.teammental.common.bll.dto.IdNameDto;
import com.teammental.common.bll.service.CommonService;
import com.teammental.common.config.TestDataGenerator;
import com.teammental.common.config.TestUtil;
import com.teammental.common.config.UrlConfig;
import com.teammental.common.dal.entity.District;
import com.teammental.common.exception.NotFoundException;
import com.teammental.memapper.MeMapper;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@SuppressWarnings("PMD.TooManyStaticImports")
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(DistrictController.class)
public class DistrictControllerTest {

  @MockBean
  private CommonService commonService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnOkAndDistricts_whenDistrictsFound() throws Exception {

    final int districtSize = 2;
    List<District> expectedDistricts = TestDataGenerator.prepareRandomListOfDistrict(districtSize);
    Optional<List<IdNameDto>> expectedDtosOptional = MeMapper.getMapperFromList(expectedDistricts)
        .mapToList(IdNameDto.class);
    List<IdNameDto> expectedDtos = expectedDtosOptional.get();

    when(commonService.getDistrictsByCityId(anyInt()))
        .thenReturn(expectedDtos);

    mockMvc.perform(get(UrlConfig.DistrictControllerConfig.URL_GET_DISTRICTS_BY_CITY_ID)
        .param("cityId","1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$", hasSize(districtSize)))
        .andExpect(jsonPath("$[0].id", is(expectedDtos.get(0).getId())))
        .andExpect(jsonPath("$[0].name", is(expectedDtos.get(0).getName())))
        .andExpect(jsonPath("$[1].id", is(expectedDtos.get(1).getId())))
        .andExpect(jsonPath("$[1].name", is(expectedDtos.get(1).getName())));

    verify(commonService, times(1)).getDistrictsByCityId(anyInt());
  }

  @Test
  public void shouldReturn404_whenNoDistrictFound() throws Exception {
    when(commonService.getDistrictsByCityId(anyInt()))
        .thenThrow(new NotFoundException(""));

    mockMvc.perform(get(UrlConfig.DistrictControllerConfig.URL_GET_DISTRICTS_BY_CITY_ID)
        .param("cityId", "1"))
        .andExpect(status().isNotFound());

    verify(commonService, times(1)).getDistrictsByCityId(anyInt());
  }
}