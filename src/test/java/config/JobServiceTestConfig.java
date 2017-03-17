package config;

import jpa.JobRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.JobService;
import service.JobServiceImpl;

/**
 * Created by Coşkun on 4.2.2017.
 */
@Configuration
public class JobServiceTestConfig {

  @Bean
  JobRepository jobRepository() {
    return Mockito.mock(JobRepository.class);
  }

  @Bean
  JobService jobService() {
    return new JobServiceImpl();
  }
}
