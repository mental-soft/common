package controller;

import dto.CityDto;
import dto.CountryDto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.CityService;
import service.CountryService;

/**
 * Created by okan on 14.03.2017.
 */
@Controller
public class CityController {

  @Autowired
  CityService cityService;

  @Autowired
  CountryService countryService;

  public static final String REQUEST_MAPPING_CITY = "/city";
  public static final String REQUEST_MAPPING_CITY_DETAIL = "/city/{id}";

  public static final String VIEW_CITY_ADD = "city/add";
  public static final String VIEW_CITY_DETAIL = "city/detail";

  public static final String MODEL_ATTRIBUTE_CITY_DTO = "cityDto";
  public static final String MODEL_ATTRIBUTE_CITY = "city";

  /**
   * Şehirler listesini getirir.
   *
   * @param model 'cities' değeri olan session modeli
   * @return city/cities' sayfası veya CityDto json listesi
   */
  @RequestMapping(value = "/cities", method = RequestMethod.GET)
  public String cityList(Model model) {
    List<CityDto> result = cityService.getAll();
    model.addAttribute("cities", result);

    return "city/cities";
  }

  /**
   * Id si verilen ülkenin şehirlerini getirir.
   *
   * @param id integer değerindeki ülke id
   * @param model 'city' değeri olan session modeli
   * @return 'city/cities' sayfası veya CityDto json sonucu
   */
  @RequestMapping(value = "/country/{id}/cities", method = RequestMethod.GET)
  public String cityListByCountry(@PathVariable(value = "id") Integer id, Model model) {
    List<CityDto> result = cityService.getAllCityByCountry(id);
    model.addAttribute("cities", result);

    return "city/cities";
  }

  /**
   * Id si verilen şehrin detayını verir.
   *
   * @param id integer değerindeki city idsi
   * @param model 'city' değeri olan session modeli
   * @return 'city/detail' sayfası veya CityDto json sonucu
   */
  @RequestMapping(value = REQUEST_MAPPING_CITY_DETAIL, method = RequestMethod.GET)
  public String cityDetail(@PathVariable(value = "id") Integer id, Model model) {
    CityDto result = null;
    try {
      result = cityService.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute(MODEL_ATTRIBUTE_CITY, result);

    return VIEW_CITY_DETAIL;
  }

  /**
   * Şehir eklemek için sayfayı hazırlar.
   *
   * @param model 'countries' ve CityDto değeri olan session modeli
   * @return 'city/add' sayfası
   */
  @RequestMapping(value = REQUEST_MAPPING_CITY, method = RequestMethod.GET)
  public String cityAdd(Model model) {
    CityDto cityDto = new CityDto();
    List<CountryDto> result = countryService.getAll();

    model.addAttribute("countries", result);
    model.addAttribute(MODEL_ATTRIBUTE_CITY_DTO, cityDto);

    return VIEW_CITY_ADD;
  }

  /**
   * Şehir ekleme veya güncelleme işlemi.
   *
   * @param cityDto Eklenmesi veya güncellenmesi istenen CityDto değeri   *
   * @return 'city/{id}' controller
   */
  @RequestMapping(value = REQUEST_MAPPING_CITY, method = RequestMethod.POST)
  public String cityPost(Model model, CityDto cityDto) {
    int id = 0;
    try {

      id = cityService.saveOrUpdate(cityDto);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/city/" + id;
  }

  /**
   * Güncelleme için sayfa hazırlar.
   *
   * @param id    Güncellenmesi istenen şehrin idsi
   * @param model 'cityDto' değeri olan session modeli
   * @return 'city/edit' sayfası veya CityDto json sonucu
   */
  @RequestMapping(value = "/city/{id}/edit", method = RequestMethod.GET)
  public String cityEdit(@PathVariable(value = "id") Integer id, Model model) {
    CityDto result = null;
    List<CountryDto> countryDtoList = countryService.getAll();

    try {
      result = cityService.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("countries", countryDtoList);
    model.addAttribute(MODEL_ATTRIBUTE_CITY_DTO, result);

    return "city/edit";
  }

  /**
   * Şehir silme sayfasını hazırlar.
   *
   * @param id    Silinmesi istenen şehrin idsi
   * @param model 'cityDto' değeri olan session model
   * @return 'city/delete' sayfası
   */
  @RequestMapping(value = "/city/{id}/delete", method = RequestMethod.GET)
  public String cityDelete(@PathVariable(value = "id") Integer id, Model model) {
    CityDto result = null;
    try {
      result = cityService.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute(MODEL_ATTRIBUTE_CITY_DTO, result);

    return "city/delete";
  }

  /**
   * Şehir silme işlemini gerçekleştirir.
   *
   * @param id Silinecek şehrin idsi
   * @return '/cities' controllerına yönlendirir.
   */
  @RequestMapping(value = "/city/delete", method = RequestMethod.POST)
  public String cityDeletePost(Integer id, Model model) {
    try {
      cityService.deleteById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "redirect:/cities";
  }

}
