package controller;

import dto.CityDto;
import dto.CountryDto;
import dto.CountryListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.CityService;
import service.CountryService;

import java.util.List;

/**
 * Created by admin on 17.3.2017.
 */
@Controller
public class CityController {

    @Autowired
    CityService cityService;

    @Autowired
    CountryService countryService;


    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public String cityList(Model model) {

        List<CityDto> cities = cityService.getAll();

        model.addAttribute("cities", cities);

        return "city/cities";
    }


    @RequestMapping(value = "/city/{id}", method = RequestMethod.GET)
    public String cityDetail(@PathVariable(value = "id") Integer id, Model model) {

        CityDto cityDto = null;
        try {
            cityDto = cityService.getByID(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("cityDto", cityDto);

        return "city/detail";
    }



    @RequestMapping(value = "/city", method = RequestMethod.GET)
    public String cityAdd(Model model) {

        CityDto cityDto = new CityDto();
        model.addAttribute("cityDto", cityDto);

        List<CountryDto> countries = countryService.getAll();
        model.addAttribute("countries", countries);

        return "city/add";
    }

    @RequestMapping(value = "/city", method = RequestMethod.POST)
    public String cityAddPost(CityDto cityDto) {

        int id = 0;
        try {
           id = cityService.saveOrUpdate(cityDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/city/" + id;
    }

}
