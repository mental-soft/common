package controller;

import dto.CityDto;
import dto.DistrictDto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import service.CityService;
import service.DistrictService;

/**
 * Created by okan on 14.03.2017.
 */
@Controller
public class DistrictController {
  @Autowired
  DistrictService districtService;

  @Autowired
  CityService cityService;

  public static final String REQUEST_MAPPING_DISTRICT = "/district";
  public static final String REQUEST_MAPPING_DISTRICT_DETAIL = "/district/{id}";

  public static final String VIEW_DISTRICT_ADD = "district/add";
  public static final String VIEW_DISTRICT_DETAIL = "district/detail";

  public static final String MODEL_ATTRIBUTE_DISTRICT_DTO = "districtDto";
  public static final String MODEL_ATTRIBUTE_DISTRICT = "district";

  /**
   * İlçeler listesini getirir.
   *
   * @param model 'districts' değeri olan session modeli
   * @return district/districts' sayfası veya DistrictDto json listesi
   */
  @RequestMapping(value = "/districts", method = RequestMethod.GET)
  public String districtList(Model model) {
    List<DistrictDto> result = districtService.getAll();
    model.addAttribute("districts", result);

    return "district/districts";
  }

  /**
   * Id si verilen şehrin ilçelerini getirir.
   *
   * @param id integer değerindeki şehir id
   * @param model 'districts' değeri olan session modeli
   * @return 'district/districts' sayfası veya DistrictDto json sonucu
   */
  @RequestMapping(value = "/city/{id}/districts", method = RequestMethod.GET)
  public String districtListByCity(@PathVariable(value = "id") Integer id, Model model) {
    List<DistrictDto> result = districtService.getAllDistrictByCity(id);
    model.addAttribute("districts", result);

    return "district/districts";
  }

  /**
   * Id si verilen ilçenin detayını verir.
   *
   * @param id integer değerindeki district idsi
   * @param model 'district' değeri olan session modeli
   * @return 'district/detail' sayfası veya CityDto json sonucu
   */
  @RequestMapping(value = REQUEST_MAPPING_DISTRICT_DETAIL, method = RequestMethod.GET)
  public String districtDetail(@PathVariable(value = "id") Integer id, Model model) {
    DistrictDto result = null;
    try {
      result = districtService.getById(id);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    model.addAttribute(MODEL_ATTRIBUTE_DISTRICT, result);

    return VIEW_DISTRICT_DETAIL;
  }

  /**
   * District eklemek için sayfayı hazırlar.
   *
   * @param model 'cities' ve DistrictDto değeri olan session modeli
   * @return 'district/add' sayfası
   */
  @RequestMapping(value = REQUEST_MAPPING_DISTRICT, method = RequestMethod.GET)
  public String districtAdd(Model model) {
    DistrictDto districtDto = new DistrictDto();
    List<CityDto> result = cityService.getAll();

    model.addAttribute("cities", result);
    model.addAttribute(MODEL_ATTRIBUTE_DISTRICT_DTO, districtDto);

    return VIEW_DISTRICT_ADD;
  }

  /**
   * Şehir ekleme veya güncelleme işlemi.
   *
   * @param districtDto Eklenmesi veya güncellenmesi istenen DistrictDto değeri   *
   * @return 'district/{id}' controller
   */
  @RequestMapping(value = REQUEST_MAPPING_DISTRICT, method = RequestMethod.POST)
  public String districtPost(Model model, DistrictDto districtDto) {
    int id = 0;
    try {

      id = districtService.saveOrUpdate(districtDto);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return "redirect:/district/" + id;
  }

  /**
   * Güncelleme için sayfa hazırlar.
   *
   * @param id    Güncellenmesi istenen ilçenin idsi
   * @param model 'districtDto' değeri olan session modeli
   * @return 'district/edit' sayfası veya DistrictDto json sonucu
   */
  @RequestMapping(value = "/district/{id}/edit", method = RequestMethod.GET)
  public String districtEdit(@PathVariable(value = "id") Integer id, Model model) {
    DistrictDto result = null;
    List<CityDto> cityDtoList = cityService.getAll();

    try {
      result = districtService.getById(id);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    model.addAttribute("cities", cityDtoList);
    model.addAttribute(MODEL_ATTRIBUTE_DISTRICT_DTO, result);

    return "district/edit";
  }

  /**
   * İlçe silme sayfasını hazırlar.
   *
   * @param id    Silinmesi istenen ilçenin idsi
   * @param model 'districtDto' değeri olan session model
   * @return 'district/delete' sayfası
   */
  @RequestMapping(value = "/district/{id}/delete", method = RequestMethod.GET)
  public String districtDelete(@PathVariable(value = "id") Integer id, Model model) {
    DistrictDto result = null;
    try {
      result = districtService.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute(MODEL_ATTRIBUTE_DISTRICT_DTO, result);

    return "district/delete";
  }

  /**
   * İlçe silme işlemini gerçekleştirir.
   *
   * @param id Silinecek ilçenin idsi
   * @return '/cities' controllerına yönlendirir.
   */
  @RequestMapping(value = "/district/delete", method = RequestMethod.POST)
  public String districtDeletePost(Integer id, Model model) {
    try {
      districtService.deleteById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/districts";
  }

}
