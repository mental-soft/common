package config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import service.CityService;
import service.CountryService;
import service.CountryServiceImpl;
import service.DistrictService;
import service.JobService;

/**
 * Created by okan on 13.05.2017.
 */
public class DistrictControllerTestConfig {

  @Bean
  public CountryService countryService() {
    return Mockito.mock(CountryService.class);
  }

  @Bean
  public CityService cityService() {
    return Mockito.mock(CityService.class);
  }

  @Bean
  public DistrictService districtService() {
    return Mockito.mock(DistrictService.class);
  }

  @Bean
  public JobService jobService() {
    return Mockito.mock(JobService.class);
  }
}
