package config;

import jpa.CityRepository;
import jpa.CountryRepository;
import jpa.DistrictRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.CityService;
import service.CityServiceImpl;
import service.CountryService;
import service.CountryServiceImpl;
import service.DistrictService;
import service.DistrictServiceImpl;

/**
 * Created by okan on 7.03.2017.
 */

@Configuration
public class CountryServiceTestConfig {

  @Bean
  CountryRepository countryRepository() {
    return Mockito.mock(CountryRepository.class);
  }

  @Bean
  CountryService countryService() {
    return new CountryServiceImpl();
  }

  @Bean
  CityService cityservice() {
    return new CityServiceImpl();
  }

  @Bean
  CityRepository cityRepository() {
    return Mockito.mock(CityRepository.class);
  }

  @Bean
  DistrictService districtService() {
    return new DistrictServiceImpl();
  }

  @Bean
  DistrictRepository districtRepository() {
    return Mockito.mock(DistrictRepository.class);
  }

}
