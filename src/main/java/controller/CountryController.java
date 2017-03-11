package controller;

import dto.CountryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.CountryService;

import java.util.List;

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



}
