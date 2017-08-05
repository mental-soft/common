package com.teammental.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.teammental.common.dal.entity.City;
import com.teammental.common.dal.entity.District;
import com.teammental.mebuilder.GenericBuilder;
import com.teammental.memapper.util.StringUtil;

/**
 * Created by sa on 5.08.2017.
 */
public class TestDataGenerator {


  public static List<City> prepareRandomListOfCities() {
    List<City> cities = prepareRandomListOfCities(10);
    return cities;
  }

  public static List<City> prepareRandomListOfCities(int size) {
    List<City> cities = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      City city = prepareRandomCity();
      cities.add(city);
    }
    return cities;
  }

  public static List<District> prepateRandomListOfDistrict(City city) {
    Random random = new Random();
    List<District> districts = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
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

  public static City prepareRandomCity() {
    Random random = new Random();
    Integer id = random.nextInt();
    String name = StringUtil.generateRandomString(5);

    City city = GenericBuilder.of(City::new)
        .with(City::setId, id)
        .with(City::setName, name)
        .build();

    List<District> districts = prepateRandomListOfDistrict(city);
    city.setDistricts(districts);

    return city;
  }
}
