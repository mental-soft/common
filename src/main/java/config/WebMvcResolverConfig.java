package config;

import constant.Encoding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
public class WebMvcResolverConfig {

  /**
   * Template resolver for view.
   * @return Servlet context template resolver
   */
  @Bean
  public ServletContextTemplateResolver templateResolver() {
    ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".html");
    resolver.setTemplateMode("HTML5");
    resolver.setCharacterEncoding(Encoding.UTF8);
    resolver.setCacheable(false);
    return resolver;
  }

  /**
   * View resolver.
   * @return ViewResolver
   */
  @Bean
  public ViewResolver thymeleafViewResolver() {
    ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
    thymeleafViewResolver.setTemplateEngine(templateEngine());
    thymeleafViewResolver.setOrder(1);
    thymeleafViewResolver.setViewNames(new String[]{"*"});
    thymeleafViewResolver.setCache(false);
    thymeleafViewResolver.setCharacterEncoding(Encoding.UTF8);
    return thymeleafViewResolver;
  }

  /**
   * Template engine.
   * @return SpringTemplateEngine
   */
  @Bean
  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine engine = new SpringTemplateEngine();
    engine.setTemplateResolver(templateResolver());

    return engine;
  }


}