package com.teammental.common.config;

import com.teammental.common.dal.entity.City;
import com.teammental.common.dal.entity.District;
import com.teammental.mebuilder.GenericBuilder;
import com.teammental.memapper.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CityAndDisctictDataGenerator {


  /**
   * Generates a random list of cities.
   * @return list of cities
   */
  public static List<City> prepareRandomListOfCities() {
    List<City> cities = prepareRandomListOfCities(10);
    return cities;
  }

  /**
   * Generates a random list of cities.
   * @param size wanted size of list
   * @return list of cities
   */
  public static List<City> prepareRandomListOfCities(int size) {
    List<City> cities = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      City city = prepareRandomCity();
      cities.add(city);
    }
    return cities;
  }

  /**
   * Generates a random list of districts.
   * @param city City object to injected in districts
   * @param size wanted size of districts
   * @return list of districts
   */
  public static List<District> prepareRandomListOfDistrict(City city, int size) {
    Random random = new Random();
    List<District> districts = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      Integer id = random.nextInt();
      String name = StringUtil.generateRandomString(5);
      District district = GenericBuilder.of(District::new)
          .with(District::setId, id)
          .with(District::setName, name)
          .with(District::setCity, city)
          .build();
      districts.add(district);
    }
    return districts;
  }

  /**
   * Generates a random list of districts.
   * @param size wanted size of list
   * @return list of districts
   */
  public static List<District> prepareRandomListOfDistrict(int size) {
    City randomCity = prepareRandomCity();
    return prepareRandomListOfDistrict(randomCity, size);
  }

  /**
   * Generates a random city.
   * @return city
   */
  public static City prepareRandomCity() {
    Random random = new Random();
    Integer id = random.nextInt();
    String name = StringUtil.generateRandomString(5);

    City city = GenericBuilder.of(City::new)
        .with(City::setId, id)
        .with(City::setName, name)
        .build();

    List<District> districts = prepareRandomListOfDistrict(city, 10);
    city.setDistricts(districts);

    return city;
  }
}
