package config;

import jpa.TitleRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.TitleService;
import service.TitleServiceImpl;

/**
 * Created by Coşkun on 4.2.2017.
 */
@Configuration
public class TitleServiceTestConfig {

    @Bean
    TitleRepository titleRepository() {
        return Mockito.mock(TitleRepository.class);
    }

    @Bean
    TitleService titleService(){
        return new TitleServiceImpl();
    }
}
