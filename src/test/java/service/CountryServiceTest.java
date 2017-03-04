package service;

import config.PersistenceContext;
import config.PersistenceContextTest;
import config.ServiceContext;
import dto.CountryDto;
import entity.Country;
import jpa.CityRepository;
import jpa.CountryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Coşkun on 4.2.2017.
 */
//@TestPropertySource({"classpath:application.properties",
//        "classpath:environment/developer.properties"})
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {PersistenceContextTest.class, ServiceContext.class})

public class CountryServiceTest {

    /*
    @Autowired
    CountryRepository countryRepository;

     @Autowired
     CountryService countryService;

//    @Before
//    public void setUp() {
//        when(countryRepository.count()).thenReturn(10L);
//    }
//
//    @Before
//    public  void setUpList() {
//
//
////        countryRepository = Mockito.mock(CountryRepository.class);
//
//
//        Country country1 = new Country();
//        country1.setName("TÜRKİYE");
//        country1.setEnName("TR");
//
//        Country country2= new Country();
//        country2.setName("İNGİLTERE");
//        country2.setEnName("EN");
//
//        List<Country> countryListTest = new ArrayList<>();
//        countryListTest.add(country1);
//        countryListTest.add(country2);
//
//        when(countryRepository.findAll()).thenReturn(countryListTest);
//
//        //when(countryRepository.delete();)

//
//    }


//    @Test
//    public void testFind() {
//
//        Country country = new Country();
//        country.setId(1);
//        country.setName("TURKIA");//
//        when(countryRepository.findOne(1)).thenReturn(country);
//    }
//    @Test
//    public void testInsertCountry(){
//
//        Country country = new Country();
//        country.setName("YeniUlke1");
//        country.setActive(true);
//        country.setEnName("NewCountry1");
//        country.setCode("YU1");
//        country.setCreatedDate(new Date());
//        country.setModifiedDate(new Date());
//
//        countryRepository.save(country);
//        // then
//        Mockito.verify(countryRepository, Mockito.times(1)).save(country);
//
//            // countryService.insertCountry(countryDto);
//    }

//    @Test
//        public void insertServiceCountry(){
//
//            CountryDto countryDto = new CountryDto();
//            countryDto.setName("YeniUlke1");
//            countryDto.setActive(true);
//            countryDto.setEnName("NewCountry1");
//            countryDto.setCode("YU1");
//            countryDto.setCreatedDate(new Date());
//            countryDto.setModifiedDate(new Date());
//
//             countryService.insertCountry(countryDto);
//
//    }

//    @Test
//    public void updateServiceCountry(){
//
//        CountryDto countryDto = new CountryDto();
//        countryDto.setId(26);
//        countryDto.setName("YeniUlke1up");
//        countryDto.setActive(true);
//        countryDto.setEnName("NewCountry1up");
//        countryDto.setCode("YU1");
//        countryDto.setCreatedDate(new Date());
//        countryDto.setModifiedDate(new Date());
//
//        CountryDto yeniDto = countryService.updateCountry(countryDto);
//
//    }
//
//    @Test
//    public void deleteServiceCountry(){
//
//        CountryDto countryDto = new CountryDto();
//        countryDto.setId(26);
//
//        countryService.deleteCountry(countryDto);
//
//    }
//
//    @Test
//    public void countryListServiceCount_ShouldReturn2(){
//        assertEquals(2,countryService.getAllCountry().size());
//    }

//    @Test
//    public void countryListServiceCount_ShouldReturn3(){
//        List<CountryDto> allcountry = countryService.getAllCountry();
//       String name = allcountry.get(0).getName();
//        assertEquals("TÜRKİYE",name);
//    }

    @Test
    public void countryService_IsNotNull() {
        assertNotNull(countryService);
    }

//    @Test
//    public void countryActiveCount_ShouldReturn10() {
//        assertEquals(10, countryService.countryActiveCount());
//    }
//
//    @Test
//    public void findAll() {
//
//        List<CountryDto> abcd = countryService.getAllCountry();
//    }

//    @Transactional
//    public void insert_Entry() {
//        Country country = new Country();
//        country.setName("Test556");
//        country.setCode("T556");
//        country.setActive(true);
//
//        country = countryRepository.save(country);
//
//        City newCity = new City();
//        newCity.setName("123");
//        newCity.setCountry(country);
//
//        cityRepository.save(newCity);
//
//        newCity = new City();
//        newCity.setName("sd33afsd");
//        newCity.setCountry(country);
//
//        cityRepository.save(newCity);
//
//        newCity = new City();
//        newCity.setName("sd444afsd");
//        newCity.setCountry(country);
//
//        cityRepository.save(newCity);
//    }
//
//    @Test
//    public void testInsertEntry() {
//        try {
//            insert_Entry();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void insert_EntryTest() {
//        Country country = new Country();
//        country.setName("Test233333");
//        country.setCode("T2333333");
//        country.setActive(true);
//
//        City newCity = new City();
//        newCity.setName("asdfsdf");
//        newCity.setCode("22");
//        newCity.setCountry(country);
//        newCity.setActive(true);
//        newCity.setBig(true);
//
//        country.getCities().add(newCity);
//
//        newCity = new City();
//        newCity.setName("333");
//        newCity.setCode("33");
//        newCity.setCountry(country);
//        newCity.setActive(true);
//        newCity.setBig(true);
//
//        country.getCities().add(newCity);
//        newCity = new City();
//        newCity.setName("4444");
//        newCity.setCode("44");
//        newCity.setCountry(country);
//        newCity.setActive(true);
//        newCity.setBig(true);
//
//        country.getCities().add(newCity);
//
//
//        countryRepository.save(country);

    }
*/
}