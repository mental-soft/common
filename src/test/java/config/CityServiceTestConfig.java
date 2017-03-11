package config;

import jpa.CityRepository;
import jpa.DistrictRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.CityService;
import service.CityServiceImpl;
import service.DistrictService;
import service.DistrictServiceImpl;

/**
 * Created by okan on 7.03.2017.
 */

@Configuration
public class CityServiceTestConfig {

    @Bean
    CityRepository cityRepository() {
        return Mockito.mock(CityRepository.class);
    }

    @Bean
    CityService cityService(){
        return new CityServiceImpl();
    }

    @Bean
    DistrictService districtService() {return new DistrictServiceImpl();}

    @Bean
    DistrictRepository districtRepository() {return Mockito.mock(DistrictRepository.class);}
}
