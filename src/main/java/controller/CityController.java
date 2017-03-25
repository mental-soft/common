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

  /**
   * Cümle gelecek.
   * @param model gelecek
   * @return gelecek
   */
  @RequestMapping(value = "/cities", method = RequestMethod.GET)
  public String cityList(Model model) {
    List<CityDto> result = cityService.getAll();
    model.addAttribute("cities", result);

    return "city/cities";
  }

  /**
   * Cümle gelecek.
   * @param id gelecek
   * @param model gelecek
   * @return gelecek
   */
  @RequestMapping(value = "/country/{id}/cities", method = RequestMethod.GET)
  public String cityListByCountry(@PathVariable(value = "id") Integer id, Model model) {
    List<CityDto> result = cityService.getAllCityByCountry(id);
    model.addAttribute("cities", result);

    return "city/cities";
  }

  /**
   * Cümle gelecek.
   * @param id city idsi
   * @param model city modeli
   * @return city detail döndürecek
   */
  @RequestMapping(value = "/city/{id}", method = RequestMethod.GET)
  public String cityDetail(@PathVariable(value = "id") Integer id, Model model) {
    CityDto result = null;
    try {
      result = cityService.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("city", result);

    return "city/detail";
  }

  /**
   * cümle gelecek.
   * @param model gelecek
   * @return gelecek
   */
  @RequestMapping(value = "/city", method = RequestMethod.GET)
  public String cityAdd(Model model) {
    CityDto cityDto = new CityDto();
    List<CountryDto> result = countryService.getAll();

    model.addAttribute("countries", result);
    model.addAttribute("cityDto", cityDto);

    return "city/add";
  }

  /**
   * Cümle gelecek.
   * @param model gelecek
   * @param cityDto gelecek
   * @return gelecek
   */
  @RequestMapping(value = "/city", method = RequestMethod.POST)
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
   * Cümle gelecek.
   * @param id gelecek
   * @param model gelecek.
   * @return gelecek
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
    model.addAttribute("cityDto", result);

    return "city/edit";
  }

  /**
   * Cümle gelecek.
   * @param id gelecek
   * @param model gelecek
   * @return gelecek
   */
  @RequestMapping(value = "/city/{id}/delete", method = RequestMethod.GET)
  public String cityDelete(@PathVariable(value = "id") Integer id, Model model) {
    CityDto result = null;
    try {
      result = cityService.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("cityDto", result);

    return "city/delete";
  }

  /**
   * Cümle gelecek.
   * @param id gelecek
   * @param model gelecek
   * @return gelecek
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
