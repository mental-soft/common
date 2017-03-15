package controller;

import dto.CityDto;
import dto.DistrictDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.CityService;
import service.DistrictService;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by okan on 14.03.2017.
 */
@Controller
public class DistrictController {
    @Autowired
    DistrictService districtService;

    @Autowired
    CityService cityService;

   @RequestMapping(value="/districts",method= RequestMethod.GET)
    public String districtList(Model model){
        List<DistrictDto> result = districtService.getAll();
        model.addAttribute("districts",result);

        return "district/districts";
    }

    @RequestMapping(value="/city/{id}/districts", method= RequestMethod.GET)
    public String districtListByCity(@PathVariable(value="id") Integer id, Model model){
        List<DistrictDto> result = districtService.getAllDistrictByCity(id);
        model.addAttribute("districts",result);

        return "district/districts";
    }

    @RequestMapping(value="/district/{id}", method = RequestMethod.GET)
    public String districtDetail(@PathVariable(value="id") Integer id, Model model){
        DistrictDto result =null;
        try{
            result = districtService.getByID(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        model.addAttribute("district",result);

        return "district/detail";
    }
    @RequestMapping(value="/district",method = RequestMethod.GET)
    public String districtAdd(Model model){
        DistrictDto districtDto = new DistrictDto();
        List<CityDto> result = cityService.getAll();

        model.addAttribute("cities", result);
        model.addAttribute("districtDto",districtDto);

        return "district/add";
    }

    @RequestMapping(value="/district",method = RequestMethod.POST)
    public String districtPost(Model model,DistrictDto districtDto, @RequestParam Integer cityID){
        int id = 0;
        try {
            CityDto cityDto =  cityService.getByID(cityID);
            districtDto.setCityDto(cityDto);
            id=districtService.saveOrUpdate(districtDto);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "redirect:/district/"+id;
    }
    @RequestMapping(value="/district/{id}/edit",method=RequestMethod.GET)
    public String districtEdit(@PathVariable(value="id") Integer id, Model model){
        DistrictDto result = null;
        List<CityDto> cityDtoList = cityService.getAll();

        try {
            result = districtService.getByID(id);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        model.addAttribute("cities", cityDtoList);
        model.addAttribute("districtDto",result);

        return "district/edit";
    }

    @RequestMapping(value="/district/{id}/delete",method = RequestMethod.GET)
    public String districtDelete(@PathVariable(value="id") Integer id, Model model){
        DistrictDto result = null;
        try{
            result =districtService.getByID(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("districtDto",result);

        return "district/delete";
    }

    @RequestMapping(value="/district/delete",method = RequestMethod.POST)
    public String districtDeletePost(Integer id,Model model){
        try{
            districtService.deleteByID(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/districts";
    }

}
