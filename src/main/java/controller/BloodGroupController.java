package controller;

import dto.BloodGroupListDto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.BloodGroupService;

/**
 * Created by Niyazi on 14.06.2017.
 */
@Controller
public class BloodGroupController {

  @Autowired
  BloodGroupService bloodGroupService;

  /**
   * Kan grupları listesini getirir.
   *
   * @param model 'bloodGroups' değeri olan session modeli
   * @return 'bloodGroup/bloodGroups' sayfası veya BloodGroupListDto json listesi
   */
  @RequestMapping(value = "/bloodGroups", method = RequestMethod.GET)
  public String bloodGroupList(Model model) {
    List<BloodGroupListDto> result = bloodGroupService.getAll();
    model.addAttribute("bloodGroups", result);

    return "bloodGroup/bloodGroups";
  }

  /**
   * Idsini verdiğiniz kan grubu detayını getirir.
   *
   * @param id    integer türündeki kan grubu idsi
   * @param model 'bloodGroup' değeri olan session modeli
   * @return 'bloodGroup/detail' sayfası veya BloodGroupListDto json sonucu
   */
  @RequestMapping(value = "/bloodGroup/{id}", method = RequestMethod.GET)
  public String bloodGroupDetail(@PathVariable(value = "id") int id, Model model) {
    BloodGroupListDto result = null;
    try {
      result = bloodGroupService.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("bloodGroup", result);

    return "bloodGroup/detail";
  }

  /**
   * Kan grubu eklemek için sayfayı hazırlar.
   *
   * @param model Boş BloodGroupListDto değeri olan session modeli
   * @return 'bloodGroup/add' sayfası
   */
  @RequestMapping(value = "/bloodGroup", method = RequestMethod.GET)
  public String bloodGroupAdd(Model model) {
    BloodGroupListDto bloodGroupListDto = new BloodGroupListDto();
    model.addAttribute("bloodGroupListDto", bloodGroupListDto);

    return "bloodGroup/add";
  }

  /**
   * Kan grubu ekleme veya güncelleme işlemi.
   *
   * @param bloodGroupListDto Eklenmesi veya güncellenmesi istenen BloodGroupListDto değeri
   * @return 'bloodGroup/{id}' controllerına yönlendirir
   */
  @RequestMapping(value = "/bloodGroup", method = RequestMethod.POST)
  public String bloodGroupPost(Model model, BloodGroupListDto bloodGroupListDto) {
    int id = 0;
    try {
      id = bloodGroupService.saveOrUpdate(bloodGroupListDto);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "redirect:/bloodGroup/" + id;
  }

  /**
   * Güncelleme için sayfayı hazırlar.
   *
   * @param id    Güncellenmesi istenen kan grubu idsi
   * @param model BloodGroupListDto değeri olan session modeli
   * @return 'bloodGroup/edit' sayfası veya BloodGroupListDto json sonucu
   */
  @RequestMapping(value = "/bloodGroup/{id}/edit", method = RequestMethod.GET)
  public String bloodGroupEdit(@PathVariable(value = "id") int id, Model model) {
    BloodGroupListDto result = null;
    try {
      result = bloodGroupService.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("bloodGroupListDto", result);

    return "bloodGroup/edit";
  }

  /**
   * Kan grubu silme sayfasını hazırlar.
   *
   * @param id    Silinmesi istenen kan grubu idsi
   * @param model BloodGroupListDto değeri olan session model
   * @return 'bloodGroup/delete' sayfası
   */
  @RequestMapping(value = "/bloodGroup/{id}/delete", method = RequestMethod.GET)
  public String bloodGroupDelete(@PathVariable(value = "id") int id, Model model) {
    BloodGroupListDto result = null;
    try {
      result = bloodGroupService.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("bloodGroupListDto", result);

    return "bloodGroup/delete";
  }

  /**
   * Kan grubu silme işlemini gerçekleştirir.
   *
   * @param id Silinecek kan grubu idsi
   * @return '/bloodGroups' controllerına yönlendirir.
   */
  @RequestMapping(value = "/bloodGroup/delete", method = RequestMethod.POST)
  public String bloodGroupDeletePost(int id) {
    try {
      bloodGroupService.deleteById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "redirect:/bloodGroups";
  }

}
