package config;

import jpa.CityRepository;
import jpa.CountryRepository;
import jpa.DistrictRepository;
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

    @Bean
    CityRepository cityRepository() {
        return Mockito.mock(CityRepository.class);
    }

    @Bean
    DistrictRepository districtRepository() {
        return Mockito.mock(DistrictRepository.class);
    }

}
