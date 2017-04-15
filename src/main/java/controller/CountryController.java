package controller;

import dto.CountryDto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.CountryService;

/**
 * Created by Coskun on 11.3.2017.
 */
@Controller
public class CountryController {

  @Autowired
  CountryService countryService;

  public static final String REQUEST_MAPPING_COUNTRY = "/country";
  public static final String REQUEST_MAPPING_COUNTRY_DETAIL = "/country/{id}";

  public static final String VIEW_COUNTRY_ADD = "country/add";
  public static final String VIEW_COUNTRY_DETAIL = "country/detail";

  public static final String MODEL_ATTRIBUTE_COUNTRY_DTO = "countryDto";
  public static final String MODEL_ATTRIBUTE_COUNTRY = "country";

  /**
   * Ülkeler listesini getirir.
   *
   * @param model 'countries' değeri olan session modeli
   * @return 'country/countries' sayfası veya CountryDto json listesi
   */
  @RequestMapping(value = "/countries", method = RequestMethod.GET)
  public String countryList(Model model) {
    List<CountryDto> result = countryService.getAll();
    model.addAttribute("countries", result);

    return "country/countries";
  }

  /**
   * Idsini verdiğiniz ülkenin detayını getirir.
   *
   * @param id    integer türündeki ülke idsi
   * @param model 'country' değeri olan session modeli
   * @return 'country/detail' sayfası veya CountryDto json sonucu
   */
  @RequestMapping(value = REQUEST_MAPPING_COUNTRY_DETAIL, method = RequestMethod.GET)
  public String countryDetail(@PathVariable(value = "id") Integer id, Model model) {
    CountryDto result = null;
    try {
      result = countryService.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute(MODEL_ATTRIBUTE_COUNTRY, result);

    return VIEW_COUNTRY_DETAIL;
  }

  /**
   * Ülke eklemek için sayfayı hazırlar.
   *
   * @param model Boş CountryDto değeri olan session modeli
   * @return 'country/add' sayfası
   */
  @RequestMapping(value = REQUEST_MAPPING_COUNTRY, method = RequestMethod.GET)
  public String countryAdd(Model model) {
    CountryDto countryDto = new CountryDto();
    model.addAttribute(MODEL_ATTRIBUTE_COUNTRY_DTO, countryDto);
    return VIEW_COUNTRY_ADD;
  }

  /**
   * Ülke ekleme veya güncelleme işlemi.
   *
   * @param countryDto Eklenmesi veya güncellenmesi istenen CountryDto değeri
   * @return 'country/{id}' controllerına yönlendirir
   */
  @RequestMapping(value = REQUEST_MAPPING_COUNTRY, method = RequestMethod.POST)
  public String countryPost(CountryDto countryDto) {
    int id = 0;
    try {
      id = countryService.saveOrUpdate(countryDto);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "redirect:/country/" + id;
  }

  /**
   * Güncelleme için sayfayı hazırlar.
   *
   * @param id    Güncellenmesi istenen ülkenin idsi
   * @param model 'countryDto' değeri olan session modeli
   * @return 'country/edit' sayfası veya CountryDto json sonucu
   */
  @RequestMapping(value = "/country/{id}/edit", method = RequestMethod.GET)
  public String countryEdit(@PathVariable(value = "id") Integer id, Model model) {
    CountryDto result = null;
    try {
      result = countryService.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("countryDto", result);

    return "country/edit";
  }

  /**
   * Ülke silme sayfasını hazırlar.
   *
   * @param id    Silinmesi istenen ülkenin idsi
   * @param model 'countryDto' değeri olan session model
   * @return 'country/delete' sayfası
   */
  @RequestMapping(value = "/country/{id}/delete", method = RequestMethod.GET)
  public String countryDelete(@PathVariable(value = "id") Integer id, Model model) {
    CountryDto result = null;
    try {
      result = countryService.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("countryDto", result);
    return "country/delete";
  }

  /**
   * Ülke silme işlemini gerçekleştirir.
   *
   * @param id Silinecek ülkenin idsi
   * @return '/countries' controllerına yönlendirir.
   */
  @RequestMapping(value = "/country/delete", method = RequestMethod.POST)
  public String countryDeletePost(Integer id) {
    try {
      countryService.deleteById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/countries";
  }
}
