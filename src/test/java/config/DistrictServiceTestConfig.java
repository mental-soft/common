package config;

import jpa.DistrictRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.DistrictService;
import service.DistrictServiceImpl;

/**
 * Created by okan on 7.03.2017.
 */

@Configuration
public class DistrictServiceTestConfig {
  @Bean
  DistrictRepository districtRepository() {
    return Mockito.mock(DistrictRepository.class);
  }

  @Bean
  DistrictService districtService() {
    return new DistrictServiceImpl();
  }
}
