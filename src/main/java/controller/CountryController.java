package controller;

import dto.CountryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.CountryService;

import java.util.List;
import javax.validation.Valid;

/**
 * Created by Coskun on 11.3.2017.
 */
@Controller
public class CountryController {

  @Autowired
  CountryService countryService;

  @RequestMapping(value = "/countries", method = RequestMethod.GET)
  public String countryList(Model model) {
    List<CountryDto> result = countryService.getAll();
    model.addAttribute("countries", result);

    return "country/countries";
  }


  @RequestMapping(value = "/country/{id}", method = RequestMethod.GET)
  public String countryDetail(@PathVariable(value = "id") Integer id, Model model) {
    CountryDto result = null;
    try {
      result = countryService.getByID(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("country", result);

    return "country/detail";
  }

  @RequestMapping(value = "/country", method = RequestMethod.GET)
  public String countryAdd(Model model) {
    CountryDto countryDto = new CountryDto();
    model.addAttribute("countryDto", countryDto);
    return "country/add";
  }

  @RequestMapping(value = "/country", method = RequestMethod.POST)
  public String countryPost(@Valid CountryDto countryDto, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      String errPage = "country/edit";
      if (countryDto.getId() == null) {
        errPage = "country/add";
      }
      return errPage;
    }

    int id = 0;
    try {
      id = countryService.saveOrUpdate(countryDto);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "redirect:/country/" + id;
  }


  @RequestMapping(value = "/country/{id}/edit", method = RequestMethod.GET)
  public String countryEdit(@PathVariable(value = "id") Integer id, Model model) {
    CountryDto result = null;
    try {
      result = countryService.getByID(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("countryDto", result);

    return "country/edit";
  }

  @RequestMapping(value = "/country/{id}/delete", method = RequestMethod.GET)
  public String countryDelete(@PathVariable(value = "id") Integer id, Model model) {
    CountryDto result = null;
    try {
      result = countryService.getByID(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("countryDto", result);

    return "country/delete";
  }

  @RequestMapping(value = "/country/delete", method = RequestMethod.POST)
  public String countryDeletePost(Integer id, Model model) {
    try {
      countryService.deleteByID(id);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "redirect:/countries";
  }
}
