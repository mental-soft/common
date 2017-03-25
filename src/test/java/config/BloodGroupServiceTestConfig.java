package config;

import jpa.BloodGroupRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.BloodGroupService;
import service.BloodGroupServiceImpl;

/**
 * Created by Coşkun on 4.2.2017.
 */
@Configuration
public class BloodGroupServiceTestConfig {

  @Bean
  BloodGroupRepository bloodGroupRepository() {
    return Mockito.mock(BloodGroupRepository.class);
  }

  @Bean
  BloodGroupService bloodGroupService() {
    return new BloodGroupServiceImpl();
  }
}
