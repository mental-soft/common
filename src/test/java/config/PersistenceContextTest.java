package config;

import jpa.CountryRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Coşkun on 4.2.2017.
 */
@Configuration
public class PersistenceContextTest {

    @Bean
    CountryRepository countryRepository() {
        return Mockito.mock(CountryRepository.class);
    }

}
