package controller;

import dto.JobListDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.JobService;

/**
 * Created by Aras on 14.04.2017.
 */
@Controller
public class JobController {

  @Autowired
  JobService jobService;

  /**
   * Meslekler listesini getirir.
   *
   * @param model 'jobs' değeri olan session modeli
   * @return 'job/jobs' sayfası veya JobListDto json listesi
   */
  @RequestMapping(value = "/jobs", method = RequestMethod.GET)
  public String jobList(Model model) {
    List<JobListDto> result = jobService.getAll();
    model.addAttribute("jobs", result);

    return "job/jobs";
  }

  /**
   * Idsini verdiğiniz meslek detayını getirir.
   *
   * @param id    integer türündeki meslek idsi
   * @param model 'job' değeri olan session modeli
   * @return 'job/detail' sayfası veya JobListDto json sonucu
   */
  @RequestMapping(value = "/job/{id}", method = RequestMethod.GET)
  public String jobDetail(@PathVariable(value = "id") int id, Model model) {
    JobListDto result = null;
    try {
      result = jobService.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("job", result);

    return "job/detail";
  }

  /**
   * Meslek eklemek için sayfayı hazırlar.
   *
   * @param model Boş JobListDto değeri olan session modeli
   * @return 'job/add' sayfası
   */
  @RequestMapping(value = "/job", method = RequestMethod.GET)
  public String jobAdd(Model model) {
    JobListDto jobListDto = new JobListDto();
    model.addAttribute("jobListDto", jobListDto);

    return "job/add";
  }

  /**
   * Meslek ekleme veya güncelleme işlemi.
   *
   * @param jobListDto Eklenmesi veya güncellenmesi istenen JobListDto değeri
   * @return 'job/{id}' controllerına yönlendirir
   */
  @RequestMapping(value = "/job", method = RequestMethod.POST)
  public String jobPost(Model model, JobListDto jobListDto) {
    int id = 0;
    try {
      id = jobService.saveOrUpdate(jobListDto);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "redirect:/job/" + id;
  }

  /**
   * Güncelleme için sayfayı hazırlar.
   *
   * @param id    Güncellenmesi istenen meslek idsi
   * @param model JobListDto değeri olan session modeli
   * @return 'job/edit' sayfası veya JobListDto json sonucu
   */
  @RequestMapping(value = "/job/{id}/edit", method = RequestMethod.GET)
  public String jobEdit(@PathVariable(value = "id") int id, Model model) {
    JobListDto result = null;
    try {
      result = jobService.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("jobListDto", result);

    return "job/edit";
  }

  /**
   * Meslek silme sayfasını hazırlar.
   *
   * @param id    Silinmesi istenen meslek idsi
   * @param model JobListDto değeri olan session model
   * @return 'job/delete' sayfası
   */
  @RequestMapping(value = "/job/{id}/delete", method = RequestMethod.GET)
  public String jobDelete(@PathVariable(value = "id") int id, Model model) {
    JobListDto result = null;
    try {
      result = jobService.getById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("jobListDto", result);

    return "job/delete";
  }

  /**
   * Meslek silme işlemini gerçekleştirir.
   *
   * @param id Silinecek meslek idsi
   * @return '/jobs' controllerına yönlendirir.
   */
  @RequestMapping(value = "/job/delete", method = RequestMethod.POST)
  public String jobDeletePost(int id) {
    try {
      jobService.deleteById(id);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "redirect:/jobs";
  }

}
