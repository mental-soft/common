package controller;

import dto.CityDto;
import dto.CountryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.BloodGroupServiceImpl;
import service.CityService;
import service.CountryService;

import java.util.List;

/**
 * Created by okan on 14.03.2017.
 */
@Controller
public class CityController {

    @Autowired
    CityService cityService;

    @Autowired
    CountryService countryService;

    @RequestMapping(value="/cities", method= RequestMethod.GET)
    public String cityList(Model model) {
        List<CityDto> result = cityService.getAll();
        model.addAttribute("cities",result);

        return "city/cities";
    }

    @RequestMapping(value="/country/{id}/cities", method=RequestMethod.GET)
    public String cityListByCountry(@PathVariable(value="id") Integer id, Model model) {
        List<CityDto> result = cityService.getAllCityByCountry(id);
        model.addAttribute("cities",result);

        return "city/cities";
    }

    @RequestMapping(value="/city/{id}", method=RequestMethod.GET)
    public String cityDetail(@PathVariable(value="id") Integer id, Model model) {
        CityDto result = null;
        try {
            result = cityService.getByID(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("city",result);

        return "city/detail";
    }

    @RequestMapping(value = "/city",method=RequestMethod.GET)
    public String cityAdd(Model model){
        CityDto cityDto = new CityDto();
        List<CountryDto> result = countryService.getAll();

        model.addAttribute("countries", result);
        model.addAttribute("cityDto",cityDto);



        return "city/add";
    }

    @RequestMapping(value="/city", method=RequestMethod.POST)
    public String cityPost(Model model, CityDto cityDto, @RequestParam Integer countryID){
        int id=0;
        try{
            CountryDto countryDto =  countryService.getByID(countryID);
            cityDto.setCountryDto(countryDto);
            id=cityService.saveOrUpdate(cityDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/city/"+id;
    }

    @RequestMapping(value="/city/{id}/edit", method=RequestMethod.GET)
    public String cityEdit(@PathVariable(value="id") Integer id, Model model){
        CityDto result = null;
        try {
            result = cityService.getByID(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("cityDto",result);

        return "city/edit";
    }

    @RequestMapping(value = "/city/{id}/delete", method = RequestMethod.GET)
    public String cityDelete(@PathVariable(value = "id") Integer id, Model model) {
        CityDto result = null;
        try {
            result = cityService.getByID(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("cityDto", result);

        return "city/delete";
    }

    @RequestMapping(value = "/city/delete", method = RequestMethod.POST)
    public String cityDeletePost(Integer id, Model model) {
        try {
            cityService.deleteByID(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/cities";
    }

}
