package config;


import constant.Encoding;
import constant.MessagesFile;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"controller"})
@Import(value = {WebMvcResolverConfig.class})
public class SpringWebContext extends WebMvcConfigurerAdapter {

  private static final String MESSAGE_SOURCE_BASE_NAME =
      MessagesFile.MESSAGES + "/" + MessagesFile.MESSAGES;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**").addResourceLocations("/static/");
  }

  @Bean
  MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
    messageSource.setUseCodeAsDefaultMessage(true);
    messageSource.setCacheSeconds(0);
    messageSource.setDefaultEncoding(Encoding.UTF8);
    return messageSource;
  }

  @Bean
  PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    HandlerInterceptorAdapter handlerInterceptorAdapter = new HandlerInterceptorAdapter() {
      @Override
      public boolean preHandle(HttpServletRequest request,
                               HttpServletResponse response, Object handler)
          throws Exception {
        request.setCharacterEncoding(Encoding.UTF8);
        response.setCharacterEncoding(Encoding.UTF8);
        return true;
      }
    };

    registry.addInterceptor(handlerInterceptorAdapter);
    registry.addInterceptor(localeChangeInterceptor());
  }

  /**
   * Locale change interceptor for internationalization.
   *
   * @return LocaleChangeInterceptor
   */
  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("lang");
    return localeChangeInterceptor;
  }

  /**
   * Locale resolver for Turkish.
   *
   * @return Default Turkish locale.
   */
  @Bean
  public SessionLocaleResolver localeResolver() {
    SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
    sessionLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("tr"));
    return sessionLocaleResolver;
  }

}
