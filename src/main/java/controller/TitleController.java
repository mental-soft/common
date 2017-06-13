package controller;

import dto.TitleListDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.TitleService;

/**
 * Created by Aras on 30.05.2017.
 */
@Controller
public class TitleController {

  @Autowired
  TitleService titleService;

  /**
   * Ünvan listesini getirir.
   *
   * @param model 'titles' değeri olan session modeli
   * @return 'title/titles' sayfası veya TitleListDto json listesi
   */
  @RequestMapping(value = "/titles", method = RequestMethod.GET)
  public String titleList(Model model) {
    List<TitleListDto> result = titleService.getAll();
    model.addAttribute("titles", result);

    return "title/titles";
  }

  /**
   * Idsini verdiğiniz ünvan detayını getirir.
   *
   * @param id    integer türündeki ünvan idsi
   * @param model 'title' değeri olan session modeli
   * @return 'title/detail' sayfası veya TitleListDto json sonucu
   */
  @RequestMapping(value = "/title/{id}", method = RequestMethod.GET)
  public String titleDetail(@PathVariable(value = "id") int id, Model model) {
    TitleListDto result = null;
    try {
      result = titleService.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("title", result);

    return "title/detail";
  }

  /**
   * Ünvan eklemek için sayfayı hazırlar.
   *
   * @param model Boş TitleListDto değeri olan session modeli
   * @return 'title/add' sayfası
   */
  @RequestMapping(value = "/title", method = RequestMethod.GET)
  public String titleAdd(Model model) {
    TitleListDto titleListDto = new TitleListDto();
    model.addAttribute("titleListDto", titleListDto);

    return "title/add";
  }


  /**
   * Ünvan ekleme veya güncelleme işlemi.
   *
   * @param titleListDto Eklenmesi veya güncellenmesi istenen TitleListDto değeri
   * @return 'title/{id}' controllerına yönlendirir
   */
  @RequestMapping(value = "/title", method = RequestMethod.POST)
  public String titlePost(Model model, TitleListDto titleListDto) {
    int id = 0;
    try {
      id = titleService.saveOrUpdate(titleListDto);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "redirect:/title/" + id;
  }

  /**
   * Güncelleme için sayfayı hazırlar.
   *
   * @param id    Güncellenmesi istenen ünvan idsi
   * @param model TitleListDto değeri olan session modeli
   * @return 'title/edit' sayfası veya TitleListDto json sonucu
   */
  @RequestMapping(value = "/title/{id}/edit", method = RequestMethod.GET)
  public String titleEdit(@PathVariable(value = "id") int id, Model model) {
    TitleListDto result = null;
    try {
      result = titleService.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("titleListDto", result);

    return "title/edit";
  }

  /**
   *  Ünvan silme sayfasını hazırlar.
   *
   * @param id    Silinmesi istenen ünvan idsi
   * @param model TitleListDto değeri olan session model
   * @return 'title/delete' sayfası
   */
  @RequestMapping(value = "/title/{id}/delete", method = RequestMethod.GET)
  public String titleDelete(@PathVariable(value = "id") int id, Model model) {
    TitleListDto result = null;
    try {
      result = titleService.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("titleListDto", result);

    return "title/delete";
  }

  /**
   * Ünvan silme işlemini gerçekleştirir.
   *
   * @param id Silinecek ünvan idsi
   * @return '/titles' controllerına yönlendirir.
   * */
  @RequestMapping(value = "/title/delete", method = RequestMethod.POST)
  public String titleDeletePost(int id) {
    try {
      titleService.deleteById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "redirect:/titles";
  }
}
