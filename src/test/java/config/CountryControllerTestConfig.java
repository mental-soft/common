package config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.CityService;
import service.CountryService;
import service.DistrictService;
import service.JobService;
import service.TitleService;

/**
 * Created by Coskun on 25.3.2017.
 */
@Configuration
public class CountryControllerTestConfig {

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

  @Bean
  public TitleService titleService() {
    return Mockito.mock(TitleService.class);
  }

}
