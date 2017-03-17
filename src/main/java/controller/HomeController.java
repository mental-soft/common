package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Coşkun on 4.3.2017.
 */
@Controller
public class HomeController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index() {
    return "index";
  }

}
